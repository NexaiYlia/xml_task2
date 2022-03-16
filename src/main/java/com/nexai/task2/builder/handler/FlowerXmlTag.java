package com.nexai.task2.builder.handler;

import com.nexai.task2.exception.ParsingXMLException;

public enum FlowerXmlTag {
    FLOWERS("flowers"),
    FLOWER("flower"),
    ROSE("rose"),
    PION("pion"),
    DATE_OF_PLANTING("date-of-planting"),
    ID("id"),
    IN_STOCK("in-stok"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visual-parameters"),
    MIN_TEMPERATURE("min-temperature"),
    LIGHTING("lighting"),
    WATERING("watering"),
    SIZE("size"),
    INFLORESCENCE_COLOR("inflorescence-color"),
    GROWING_TIPS("growing-tips"),
    MULTIPLYING("multiplying"),
    SPIKES("spikes"),
    NUMBER_PEDUNCLES("number-peduncles"),
    STEM_THICKNESS("stem-thickness"),
    FEEDING_FREQUENCY("feeding-frequency");

    private String value;

    FlowerXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FlowerXmlTag getXmlTag(String name) throws ParsingXMLException {
        for (FlowerXmlTag tag : FlowerXmlTag.values()) {
            if (name.equals(tag.getName())) {
                return tag;
            }
        }
        throw new ParsingXMLException("Unknown tag <" + name + ">");
    }

    public String getName() {
        return value;
    }
}

