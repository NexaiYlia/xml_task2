package com.nexai.task2.exception;

public class ParsingXMLException extends Exception {
    public ParsingXMLException() {
    }

    public ParsingXMLException(String message) {
        super(message);
    }

    public ParsingXMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingXMLException(Throwable cause) {
        super(cause);
    }
}
