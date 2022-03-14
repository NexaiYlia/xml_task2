package com.nexai.task2.builder.handler;

import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private final EnumSet<FlowerXmlTag> withText;
    private Set<Flower> flowers;
    private Flower currentFlower;
    private Rose currentRose;
    private Pion currentPion;
    private Rhododendron currentRhododendron;
    private Orchid currentOrchid;
    private FlowerXmlTag currentXmlTag;
    private GrowingTips currentGrowingTips;
    private VisualParameters currentVisualParams;


    public FlowerHandler() {
        flowers = new HashSet<Flower>();
        withText = EnumSet.range(FlowerXmlTag.NAME, FlowerXmlTag.ORIGIN);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }


    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        if (qName.equals(FlowerXmlTag.ROSE.getValue())) {
            currentRose = new Rose();
            currentRose.setId(attrs.getValue(0));
        } else if (qName.equals(FlowerXmlTag.PION.getValue())) {
            currentPion = new Pion();
            currentPion.setId(attrs.getValue(0));
        } else if (qName.equals(FlowerXmlTag.RHODODENDRON.getValue())) {
            currentRhododendron = new Rhododendron();
            currentRhododendron.setId(attrs.getValue(0));
        } else if (qName.equals(FlowerXmlTag.ORCHID.getValue())) {
            currentOrchid = new Orchid();
            currentOrchid.setId(attrs.getValue(0));
        } else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        String roseTag = FlowerXmlTag.ROSE.getValue();
        String piontTag = FlowerXmlTag.PION.getValue();
        String rhododendronTag = FlowerXmlTag.RHODODENDRON.getValue();
        String orchidTag = FlowerXmlTag.ORCHID.getValue();
        if (qName.equals(roseTag) || qName.equals(piontTag) || qName.equals(rhododendronTag) || qName.equals(orchidTag)) {
            flowers.add(currentFlower);
            currentFlower = null;
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case ID -> currentFlower.setId(data);
                case NAME -> currentFlower.setName(data);
                case DATE_OF_PLANTING -> currentFlower.setDateOfPlanting(YearMonth.parse(data));
                case SOIL -> currentFlower.setSoil(Soil.getSoil(data));
                case ORIGIN -> currentFlower.setOrigin(data);
                case MULTIPLYING -> currentFlower.setMultiplying(Multiplying.getMultiplying(data));
                case SPIKES -> {
                    currentRose = (Rose) currentFlower;
                    currentRose.setSpikes(Boolean.parseBoolean(data));
                }
                case NUMBER_PEDUNCLES -> {
                    currentPion = (Pion) currentFlower;
                    currentPion.setNumberPeduncles(Integer.parseInt(data));
                }
                case STEM_THICKNESS -> {
                    currentRhododendron = (Rhododendron) currentFlower;
                    currentRhododendron.setStemThickness(Integer.parseInt(data));
                }
                case FEEDING_FREQUENCY -> {
                    currentOrchid = (Orchid) currentFlower;
                    currentOrchid.setFeedingFrequency(data);
                }

                case SIZE -> {
                    VisualParameters visualParameters = currentFlower.getVisualParameters();
                    visualParameters.setSize(Double.parseDouble(data));
                    currentFlower.setVisualParameters(visualParameters);
                }
                case INFLORESCENCE_COLOR -> {
                    VisualParameters visualParameters = currentFlower.getVisualParameters();
                    visualParameters.setInflorescence_color(data);
                    currentFlower.setVisualParameters(visualParameters);
                }

                case MIN_TEMPERATURE -> {
                    GrowingTips growingTips = currentFlower.getGrowingTips();
                    growingTips.setMinTemperature(Integer.parseInt(data));
                    currentFlower.setGrowingTips(growingTips);
                }
                case LIGHTING -> {
                    GrowingTips growingTips = currentFlower.getGrowingTips();
                    growingTips.setLighting(data);
                    currentFlower.setGrowingTips(growingTips);

                }
                case WATERING -> {
                    GrowingTips growingTips = currentFlower.getGrowingTips();
                    growingTips.setWatering(data);
                    currentFlower.setGrowingTips(growingTips);

                }
                default -> new ParsingXMLException("Data not exist");
            }
        }
        currentXmlTag = null;
    }
}







