package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlType;


@XmlType(name = "growing-tips")
public class GrowingTips {
    private String watering;
    private String lighting;
    private int minTemperature;

    public GrowingTips() {
    }

    public GrowingTips(int minTemperature, String lighting, String watering) {
        this.minTemperature = minTemperature;
        this.lighting = lighting;
        this.watering = watering;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrowingTips that = (GrowingTips) o;

        if (minTemperature != that.minTemperature) return false;
        if (watering != null ? !watering.equals(that.watering) : that.watering != null) return false;
        return lighting != null ? lighting.equals(that.lighting) : that.lighting == null;
    }

    @Override
    public int hashCode() {
        int result = watering != null ? watering.hashCode() : 0;
        result = 31 * result + (lighting != null ? lighting.hashCode() : 0);
        result = 31 * result + minTemperature;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GrowingTips{");
        sb.append("watering='").append(watering).append('\'');
        sb.append(", lighting='").append(lighting).append('\'');
        sb.append(", minTemperature=").append(minTemperature);
        sb.append('}');
        return sb.toString();
    }
}
