package com.animal.Entity;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class SpinId {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spinId;

    private LocalDateTime creationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpinId() {
		return spinId;
	}

	public void setSpinId(String spinId) {
		this.spinId = spinId;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public SpinId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpinId(Long id, String spinId, LocalDateTime creationTime) {
		super();
		this.id = id;
		this.spinId = spinId;
		this.creationTime = creationTime;
	}
    
    
    
    
}
