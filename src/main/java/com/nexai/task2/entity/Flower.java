package com.nexai.task2.entity;


import com.nexai.task2.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.YearMonth;

@XmlType(propOrder = {"name", "dateOfPlanting", "soil", "origin", "visualParameters", "growingTips", "multiplying"}, name = "flower")
public class Flower {
    private String id;
    private boolean inStok;
    private String name;
    private YearMonth dateOfPlanting;
    private Soil soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Multiplying multiplying;

    public Flower() {
    }

    public Flower(String id, boolean inStok, String name, YearMonth dateOfPlanting, Soil soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying) {
        this.id = id;
        this.inStok = inStok;
        this.name = name;
        this.dateOfPlanting = dateOfPlanting;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
    }

    @XmlElement(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "in-stock")
    public Boolean isInStok(boolean b) {
        return inStok;
    }

    public void setInStok(boolean inStok) {
        this.inStok = inStok;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    @XmlElement(name = "date-of-planting")
    public YearMonth getDateOfPlanting() {
        return dateOfPlanting;
    }

    public void setDateOfPlanting(YearMonth dateOfPlanting) {
        this.dateOfPlanting = dateOfPlanting;
    }

    @XmlElement(name = "soil")
    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    @XmlElement(name = "origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @XmlElement(name = "visualParameters")
    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    @XmlElement(name = "growingTips")
    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;

    }

    @XmlElement(name = "multiplying")
    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower fLower = (Flower) o;

        if (id != null ? !id.equals(fLower.id) : fLower.id != null) return false;
        if (name != null ? !name.equals(fLower.name) : fLower.name != null) return false;
        if (dateOfPlanting != null ? !dateOfPlanting.equals(fLower.dateOfPlanting) : fLower.dateOfPlanting != null)
            return false;
        if (soil != null ? !soil.equals(fLower.soil) : fLower.soil != null) return false;
        if (origin != null ? !origin.equals(fLower.origin) : fLower.origin != null) return false;
        if (visualParameters != null ? !visualParameters.equals(fLower.visualParameters) : fLower.visualParameters != null)
            return false;
        if (growingTips != null ? !growingTips.equals(fLower.growingTips) : fLower.growingTips != null) return false;
        return multiplying != null ? multiplying.equals(fLower.multiplying) : fLower.multiplying == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateOfPlanting != null ? dateOfPlanting.hashCode() : 0);
        result = 31 * result + (soil != null ? soil.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        result = 31 * result + (growingTips != null ? growingTips.hashCode() : 0);
        result = 31 * result + (multiplying != null ? multiplying.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flower{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dateOfPlanting=").append(dateOfPlanting);
        sb.append(", soil=").append(soil);
        sb.append(", origin='").append(origin).append('\'');
        sb.append(", visualParameters=").append(visualParameters);
        sb.append(", growingTips=").append(growingTips);
        sb.append(", multiplying=").append(multiplying);
        sb.append('}');
        return sb.toString();
    }
}

