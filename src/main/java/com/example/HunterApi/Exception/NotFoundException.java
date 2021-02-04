package com.example.HunterApi.Exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class NotFoundException extends  RuntimeException {
    public NotFoundException (final String message){super(message);}
}
