package com.example.gm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorInfo {

    private String url;

    private String details;

    private String timestamp;
}
