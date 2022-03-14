package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "rhododendron")
@XmlRootElement
public class Rhododendron extends Flower{
    private double stemThickness;

    public Rhododendron() {
        super();
    }

    public Rhododendron(String id, String name, YearMonth dateOfPlanting, Soil soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, double stemThickness) {
        super(id, name, dateOfPlanting, soil, origin, visualParameters, growingTips, multiplying);
        this.stemThickness = stemThickness;
    }
    @XmlElement(name = "stem-thickness")
    public double getStemThickness() {
        return stemThickness;
    }

    public void setStemThickness(double stemThickness) {
        this.stemThickness = stemThickness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Rhododendron that = (Rhododendron) o;

        return Double.compare(that.stemThickness, stemThickness) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(stemThickness);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rhododendron{");
        sb.append("stemThickness=").append(stemThickness);
        sb.append('}');
        return sb.toString();
    }
}
