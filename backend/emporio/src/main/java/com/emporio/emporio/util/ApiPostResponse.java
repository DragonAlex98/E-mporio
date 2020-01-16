package com.emporio.emporio.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiPostResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiPostResponse {

    private String message;
    
}