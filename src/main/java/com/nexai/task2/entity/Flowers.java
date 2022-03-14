package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class Flowers {

    Set<Flower> flowers;

    public Set<Flower> getFlowers() {
        return flowers;
    }
    @XmlElementWrapper
    @XmlElements({
            @XmlElement(type = Rose.class, name = "rose"),
            @XmlElement(type = Pion.class, name = "pion"),
            @XmlElement(type = Rhododendron.class, name = "rhododendron"),
            @XmlElement(type = Orchid.class, name = "orchid")
    })
    public void setFlowers(Set<Flower> flowers) {
        this.flowers = flowers;
    }

    public void add(Flower flower) {
        if (this.flowers == null) {
            this.flowers = new HashSet<>();
        }
        this.flowers.add(flower);
    }
}


