package com.nexai.task2.builder;

import com.nexai.task2.entity.Flower;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerBuilder {
    protected Set<Flower> flowers = new HashSet<>();

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public abstract void buildSetFlowers(String fileName);
}

