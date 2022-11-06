package com.adidas.backend.emailservice.service;

import com.adidas.backend.emailservice.event.Event;

public interface SubscriptionEventsService {
    void consumer (Event<?> event);
}
