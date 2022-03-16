package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "pion")
@XmlRootElement
public class Rose extends Flower {
    private boolean spikes;

    public Rose() {

    }

    public Rose(String id, boolean inStok, String name, YearMonth dateOfPlanting, Soil soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, boolean spikes) {
        super(id, inStok, name, dateOfPlanting, soil, origin, visualParameters, growingTips, multiplying);
        this.spikes = spikes;
    }

    @XmlElement(name = "spikes")
    public boolean isSpikes() {
        return spikes;
    }

    public void setSpikes(boolean spikes) {
        this.spikes = spikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rose rose = (Rose) o;

        return spikes == rose.spikes;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (spikes ? 1 : 0);
        return result;
    }

}
