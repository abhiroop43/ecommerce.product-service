package dev.abhiroopsantra.productservice.dto;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor public class ApiResponse {
    public String                  errCode;
    public String                  errMessage;
    public HashMap<String, Object> data;
}
