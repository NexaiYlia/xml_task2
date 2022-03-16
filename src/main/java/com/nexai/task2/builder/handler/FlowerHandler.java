package com.nexai.task2.builder.handler;

import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String ELEMENT_ROSE = "rose";
    private static final String ELEMENT_PION = "pion";
    private final EnumSet<FlowerXmlTag> withText;
    private Set<Flower> flowers;
    private Flower currentFlower;
    private Rose currentRose;
    private Pion currentPion;
    private FlowerXmlTag currentXmlTag;


    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.NAME, FlowerXmlTag.ORIGIN);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_ROSE.equals(qName)) {
            currentRose = new Rose();
            currentRose.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                currentRose.setInStok(Boolean.parseBoolean(attrs.getValue(1)));
            }
            currentFlower = currentRose;

        } else if (ELEMENT_PION.equals(qName)) {
            currentPion = new Pion();
            currentPion.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                currentPion.setInStok(Boolean.parseBoolean(attrs.getValue(1)));
            }
            currentFlower = currentPion;
        } else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_ROSE.equals(qName) || ELEMENT_PION.equals(qName)) {
            flowers.add(currentFlower);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> currentFlower.setName(data);
                case DATE_OF_PLANTING -> currentFlower.setDateOfPlanting(YearMonth.parse(data));
                case SOIL -> {
                    try {
                        currentFlower.setSoil(Soil.getSoil(data));
                    } catch (ParsingXMLException e) {
                        logger.error("Soil not exist" + data + e);
                    }
                }
                case ORIGIN -> currentFlower.setOrigin(data);
                case MULTIPLYING -> {
                    try {
                        currentFlower.setMultiplying(Multiplying.getMultiplying(data));
                    } catch (ParsingXMLException e) {
                        e.printStackTrace();
                    }
                }
                case SPIKES -> {
                    currentRose = (Rose) currentFlower;
                    currentRose.setSpikes(Boolean.parseBoolean(data));
                }
                case NUMBER_PEDUNCLES -> {
                    currentPion = (Pion) currentFlower;
                    currentPion.setNumberPeduncles(Integer.parseInt(data));
                }
                case SIZE -> {
                    currentFlower.getVisualParameters().setSize(Double.parseDouble(data));
                }
                case INFLORESCENCE_COLOR -> {
                    currentFlower.getVisualParameters().setInflorescenceColor(data);
                }
                case MIN_TEMPERATURE -> {
                    currentFlower.getGrowingTips().setMinTemperature(Integer.parseInt(data));
                }
                case LIGHTING -> {
                    currentFlower.getGrowingTips().setLighting(data);
                }
                case WATERING -> {
                    currentFlower.getGrowingTips().setWatering(data);
                }
                default -> {
                    logger.error("Unknown name " + currentXmlTag.name());
                    new ParsingXMLException("Data not exist");
                }
            }
        }
        currentXmlTag = null;
    }
}
