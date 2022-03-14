package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlType;


@XmlType(name = "growing-tips")
public class VisualParameters{
    private String inflorescence_color;
    private double size;

    public VisualParameters() {
    }

    public VisualParameters(String inflorescence_color, double size) {
        this.inflorescence_color = inflorescence_color;
        this.size = size;
    }

    public String getInflorescence_color(String data) {
        return inflorescence_color;
    }

    public void setInflorescence_color(String inflorescence_color) {
        this.inflorescence_color = inflorescence_color;
    }

    public double getSize(double v) {
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
        return inflorescence_color != null ? inflorescence_color.equals(that.inflorescence_color) : that.inflorescence_color == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = inflorescence_color != null ? inflorescence_color.hashCode() : 0;
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VisualParameters{");
        sb.append("inflorescence_color='").append(inflorescence_color).append('\'');
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
