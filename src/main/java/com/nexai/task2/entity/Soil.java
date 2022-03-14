package com.nexai.task2.entity;

import com.nexai.task2.exception.ParsingXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "soil")
@XmlEnum
public enum Soil {
    @XmlEnumValue("loamy")
    LOAMY("loamy"),
    @XmlEnumValue("podzolic")
    PODZOLIC("podzolic"),
    @XmlEnumValue("ground")
    GROUND("ground"),
    @XmlEnumValue("lime")
    LIME("lime"),
    @XmlEnumValue("tree bark")
    TREE_BARK("tree bark");

    private String name;

    Soil(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Soil getSoil(String name) {
        for (Soil soil : Soil.values()) {
            if (name.equalsIgnoreCase(soil.getName())) {
                return soil;
            }
        }
        return null;//todo smth
    }
}
