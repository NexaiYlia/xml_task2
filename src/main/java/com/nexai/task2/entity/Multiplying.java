package com.nexai.task2.entity;

import com.nexai.task2.exception.ParsingXMLException;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "multiplying")
@XmlEnum
public enum Multiplying {
    @XmlEnumValue("leaves")
    LEAVES("leaves"),
    @XmlEnumValue("seeds")
    SEED("seeds"),
    @XmlEnumValue("cuttings")
    CUTTINGS("cuttings"),
    @XmlEnumValue("vegetative")
    VEGETATIVE("vegetative");

    private String name;

    Multiplying(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Multiplying getMultiplying(String name) throws ParsingXMLException {
        for (Multiplying multiplying : Multiplying.values()) {
            if (name.equalsIgnoreCase(multiplying.getName())) {
                return multiplying;
            }
        }
        throw new ParsingXMLException("Production with name '" + name + "' doesn't exist");
    }
}
