package com.nexai.task2.builder.handler;

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

