package com.nexai.task2.builder.handler;

public enum FlowerXmlTag {
    FLOWERS("flowers"),
    ROSE("rose"),
    PION("pion"),
    RHODODENDRON("rhododendron"),
    ORCHID("orchid"),
    DATE_OF_PLANTING("date-of-planting"),
    ID("id"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visual-parameters"),
    MIN_TEMPERATURE("min-temperature"),
    LIGHTING("lighting"),
    WATERING("watering"),
    SIZE("size"),
    INFLORESCENCE_COLOR("inflorescence-color"),
    GROWING_TIPS("growing_tips"),
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
}
