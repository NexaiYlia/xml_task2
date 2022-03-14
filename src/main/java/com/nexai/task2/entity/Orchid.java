package com.nexai.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "orchid")
@XmlRootElement
public class Orchid extends Flower{
    private String feedingFrequency;

    public Orchid() {
        super();
    }

    public Orchid(String id, String name, YearMonth dateOfPlanting, Soil soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, Multiplying multiplying, String feedingFrequency) {
        super(id, name, dateOfPlanting, soil, origin, visualParameters, growingTips, multiplying);
        this.feedingFrequency = feedingFrequency;
    }
    @XmlElement(name = "feeding-frequency")
    public String getFeedingFrequency() {
        return feedingFrequency;
    }

    public void setFeedingFrequency(String feedingFrequency) {
        this.feedingFrequency = feedingFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Orchid orchid = (Orchid) o;

        return feedingFrequency != null ? feedingFrequency.equals(orchid.feedingFrequency) : orchid.feedingFrequency == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (feedingFrequency != null ? feedingFrequency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Orchid{");
        sb.append("feedingFrequency='").append(feedingFrequency).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
