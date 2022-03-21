package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class Flowers {

    private Set<Flower> flowers;

    public Flowers() {
        this.flowers = new HashSet<>();
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }


    @XmlElements({
            @XmlElement(type = Rose.class, name = "rose-flower"),
            @XmlElement(type = Pion.class, name = "pion-flower"),

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flowers flowers1 = (Flowers) o;

        return flowers != null ? flowers.equals(flowers1.flowers) : flowers1.flowers == null;
    }

    @Override
    public int hashCode() {
        return flowers != null ? flowers.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flowers{");
        sb.append("flowers=").append(flowers);
        sb.append('}');
        return sb.toString();
    }
}


