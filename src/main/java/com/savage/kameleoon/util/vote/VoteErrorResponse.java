package com.savage.kameleoon.util.vote;

import java.time.LocalDateTime;

public class VoteErrorResponse {
    private  String message;
    private LocalDateTime timestamp;

    public VoteErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now() ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
