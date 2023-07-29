package com.userSystem.Exception;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String path;
    private String errorCode;

    public ErrorDetails(LocalDateTime timeStamp, String message, String path, String errorCode) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.path = path;
        this.errorCode = errorCode;
    }
}
