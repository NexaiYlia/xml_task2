package com.nexai.task2.parser.builder;

import com.nexai.task2.entity.Flower;
import com.nexai.task2.exception.ParsingXMLException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerBuilder {
    public static final String SCHEMA = "flowers.xsd";
    protected Set<Flower> flowers = new HashSet<>();

    public Set<Flower> getFlowers() {
        return flowers;
    }


    public abstract void buildSetFlowers(String fileName) throws ParsingXMLException;
}


