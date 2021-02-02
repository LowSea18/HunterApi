package com.example.HunterApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubNameInfo {
    private Long id;
    private String name;
}
