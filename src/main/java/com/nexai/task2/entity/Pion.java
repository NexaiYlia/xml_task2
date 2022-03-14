package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "pion")
@XmlRootElement
public class Pion extends Flower{
    private int numberPeduncles;

    public Pion() {
        super();
    }

    public Pion(String id, String name, YearMonth dateOfPlanting, Soil soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, int numberPeduncles) {
        super(id, name, dateOfPlanting, soil, origin, visualParameters, growingTips, multiplying);
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
        final StringBuilder sb = new StringBuilder("Pion{");
        sb.append("numberPeduncles=").append(numberPeduncles);
        sb.append('}');
        return sb.toString();
    }
}