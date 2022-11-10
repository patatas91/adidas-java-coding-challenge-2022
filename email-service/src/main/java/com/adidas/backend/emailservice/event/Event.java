package com.adidas.backend.emailservice.event;

import lombok.Data;

import java.util.Date;

@Data
public abstract class Event<T> {
    private String id;
    private T data;
}
