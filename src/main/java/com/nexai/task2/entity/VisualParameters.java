package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlType;


@XmlType(name = "growing-tips")
public class VisualParameters {
    private String inflorescenceColor;
    private double size;

    public VisualParameters() {
    }

    public VisualParameters(String inflorescenceColor, double size) {
        this.inflorescenceColor = inflorescenceColor;
        this.size = size;
    }

    public String getInflorescenceColor() {
        return inflorescenceColor;
    }

    public void setInflorescenceColor(String inflorescenceColor) {
        this.inflorescenceColor = inflorescenceColor;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisualParameters that = (VisualParameters) o;

        if (Double.compare(that.size, size) != 0) return false;
        return inflorescenceColor != null ? inflorescenceColor.equals(that.inflorescenceColor) : that.inflorescenceColor == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = inflorescenceColor != null ? inflorescenceColor.hashCode() : 0;
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VisualParameters{");
        sb.append("inflorescence_color='").append(inflorescenceColor).append('\'');
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
