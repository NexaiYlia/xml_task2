package com.nexai.task2.parser;

public enum FlowerXmlAttribute {
    ID("id"),
    IN_STOCK("in-stock");

    private String name;

    FlowerXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

