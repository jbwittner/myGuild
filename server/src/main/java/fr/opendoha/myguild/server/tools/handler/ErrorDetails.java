package fr.opendoha.myguild.server.tools.handler;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String exception;

    public ErrorDetails(Date timestamp, String message, String details, String exception) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.exception = exception;
    }

}