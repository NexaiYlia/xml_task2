package com.nexai.task2.builder.handler;

public enum FlowerXmlAttribute {
    ID("id");

    private String name;

    FlowerXmlAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

