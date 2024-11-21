package com.bajaj.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bajaj.model.DataRequest;

@RestController
public class BFHLController {
    // GET method
    @GetMapping("/bfhl")
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        Map<String,Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    // POST method
    @PostMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> processData(@RequestBody DataRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        // Validation aur processing logic
        response.put("is_success", true);
        response.put("user_id", "your_name_ddmmyyyy");
        response.put("email", "your_email@example.com");
        response.put("roll_number", "ABCD123");
        
        // Numbers filter
        List<String> numbers = request.getData().stream()
            .filter(item -> item.matches("\\d+"))
            .collect(Collectors.toList());
        response.put("numbers", numbers);
        
        // Alphabets filter
        List<String> alphabets = request.getData().stream()
            .filter(item -> item.matches("[a-zA-Z]"))
            .collect(Collectors.toList());
        response.put("alphabets", alphabets);
        
        // Highest lowercase alphabet
        Optional<String> highestAlphabet = alphabets.stream()
            .filter(item -> item.matches("[a-z]"))
            .max(Comparator.naturalOrder());
        response.put("highest_lowercase_alphabet", 
            highestAlphabet.map(Collections::singletonList)
            .orElse(Collections.emptyList()));
        
        // Prime number check
        boolean isPrimeFound = numbers.stream()
            .anyMatch(this::isPrime);
        response.put("is_prime_found", isPrimeFound);
        
        // File handling
        response.put("file_valid", false);
        response.put("file_mime_type", "");
        response.put("file_size_kb", 0);
        
        return ResponseEntity.ok(response);
    }

    // Prime number helper method
    private boolean isPrime(String numberStr) {
        int number = Integer.parseInt(numberStr);
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}