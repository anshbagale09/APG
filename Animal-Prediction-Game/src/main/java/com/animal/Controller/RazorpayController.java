package com.animal.Controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RazorpayController {

    @Value("${razorpay.keyId}")
    private String razorpayKeyId;

    @Value("${razorpay.keySecret}")
    private String razorpayKeySecret;

    @PostMapping("/create-razorpay-order")
    public ResponseEntity<Map<String, String>> createRazorpayOrder(@RequestBody Map<String, String> request) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            // Amount in rupees directly entered by the user
            Double amountInRupees = Double.parseDouble(request.get("amount"));

            // Check if the amount is at least 1 INR
            if (amountInRupees < 1.0) {
                throw new RazorpayException("BAD_REQUEST_ERROR: The amount must be at least INR 1.00");
            }

            // Create an order
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amountInRupees * 100); // Convert to paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());
            orderRequest.put("payment_capture", 1);

            Order order = razorpayClient.orders.create(orderRequest);

            Map<String, String> response = new HashMap<>();
            response.put("order_id", order.get("id"));

            return ResponseEntity.ok(response);
        } catch (RazorpayException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }




    @PostMapping("/verify-razorpay-payment")
    public ResponseEntity<String> verifyRazorpayPayment(@RequestBody Map<String, String> response) {
        try {
            String orderId = response.get("order_id");
            String paymentId = response.get("payment_id");
            String signature = response.get("razorpay_signature");

            boolean isSignatureValid = verifySignature(orderId, paymentId, signature);

            if (isSignatureValid) {
                // Signature is valid, perform additional logic
                return ResponseEntity.ok("Payment successful");
            } else {
                return ResponseEntity.status(500).body("Payment verification failed: Invalid signature");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Payment verification failed: " + e.getMessage());
        }
    }

    private boolean verifySignature(String orderId, String paymentId, String signature) {
        try {
            String concatenatedString = orderId + "|" + paymentId;
            String generatedSignature = generateHmacSHA256(concatenatedString, razorpayKeySecret);
            return generatedSignature.equals(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateHmacSHA256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKeySpec);
        byte[] signatureBytes = sha256Hmac.doFinal(data.getBytes());
        return bytesToHex(signatureBytes);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }
}
