package com.animal.ServiceImpl;

import com.animal.Entity.SpinId;
import com.animal.Repository.SpinIdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TimerService {

    private long timerDuration = 120000; // 120000 milliseconds = 2 minutes

    private long serverStartTime = 0;

    @Autowired
    private SpinIdRepository spinIdRepository;

    public TimerResponse startOrResetTimer() {
        long currentTime = Instant.now().toEpochMilli();

        // Calculate the time elapsed since the last spin ID generation
        long timeElapsed = currentTime - serverStartTime;

        // Check if at least 2 minutes have passed since the last spin ID generation
        if (serverStartTime == 0 || timeElapsed >= timerDuration) {
            // If the timer is not started or has expired, set the server start time
            serverStartTime = currentTime;

            // Generate a new spin ID
            String spinIdValue = generateSpinId();
            saveSpinIdToDatabase(spinIdValue);

            return new TimerResponse(spinIdValue, serverStartTime, timerDuration);
        }

        // If less than 2 minutes have passed, reuse the existing spin ID
        SpinId existingSpinId = spinIdRepository.findFirstByCreationTimeAfterOrderByCreationTimeDesc(
                LocalDateTime.now().minus(Duration.ofMillis(timerDuration)));

        if (existingSpinId != null) {
            return new TimerResponse(existingSpinId.getSpinId(), serverStartTime, timerDuration);
        }

        // If no valid spin ID is found, generate a new one
        String spinIdValue = generateSpinId();
        saveSpinIdToDatabase(spinIdValue);

        return new TimerResponse(spinIdValue, serverStartTime, timerDuration);
    }

    public TimerResponse getTimerStatus() {
        // Get the current time in milliseconds using Instant.now()
        long currentTime = Instant.now().toEpochMilli();

        // Calculate the remaining time by subtracting the time elapsed since server start
        long remainingTime = timerDuration - (currentTime - serverStartTime);

        // Create and return a TimerResponse object with relevant information
        return new TimerResponse(null, serverStartTime, remainingTime);
    }


    private String generateSpinId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        return now.format(formatter);
    }

    private boolean isSpinIdSavedWithinCurrentTimeFrame = false;

    private void saveSpinIdToDatabase(String spinIdValue) {
        if (!isSpinIdSavedWithinCurrentTimeFrame) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime timeFrameStart = currentDateTime.minus(Duration.ofMillis(timerDuration));

            SpinId existingSpinId = spinIdRepository.findFirstByCreationTimeAfterOrderByCreationTimeDesc(timeFrameStart);

            if (existingSpinId != null) {
                // Reuse existing spin ID if found within the current time frame
                existingSpinId.setCreationTime(currentDateTime);
                spinIdRepository.save(existingSpinId);
            } else {
                // Create a new spin ID if none is found within the current time frame
                SpinId spinIdEntity = new SpinId();
                spinIdEntity.setSpinId(spinIdValue);
                spinIdEntity.setCreationTime(currentDateTime);
                spinIdRepository.save(spinIdEntity);
            }

            // Set the flag to indicate that a spin ID has been saved within the current time frame
            isSpinIdSavedWithinCurrentTimeFrame = true;

            // Schedule a reset of the flag after the timer duration to allow saving a new spin ID in the next time frame
            Executors.newScheduledThreadPool(1).schedule(() -> {
                isSpinIdSavedWithinCurrentTimeFrame = false;
            }, timerDuration, TimeUnit.MILLISECONDS);
        }
    }


}
