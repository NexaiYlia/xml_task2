package com.nexai.task2.parser;

public enum TypeParser {
    SAX,
    STAX,
    DOM;

    private String type;

    TypeParser() {
    }

    TypeParser(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
