package com.bajaj.model;

import java.util.List;

import lombok.Data;

@Data
public class DataRequest {
    private List<String> data;
    private String file_b64; 
}