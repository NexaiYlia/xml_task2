package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "pion-flower")
@XmlRootElement
public class Pion extends Flower {
    private int numberPeduncles;

    public Pion() {
    }

    public Pion(String id, boolean inStok, String name, YearMonth dateOfPlanting, Soil soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, int numberPeduncles) {
        super(id, inStok, name, dateOfPlanting, soil, origin, visualParameters, growingTips, multiplying);
        this.numberPeduncles = numberPeduncles;
    }

    @XmlElement(name = "number-peduncles")
    public int getNumberPeduncles() {
        return numberPeduncles;
    }

    public void setNumberPeduncles(int numberPpeduncles) {
        this.numberPeduncles = numberPpeduncles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pion pion = (Pion) o;

        return numberPeduncles == pion.numberPeduncles;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numberPeduncles;
        return result;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        StringBuilder builder = new StringBuilder(className);
        builder.append('{').append(super.toString()).
                append("numberPeduncles=").append(numberPeduncles).
                append("}\n");
        return builder.toString();
    }

}
