package com.nexai.task2.parser.handler;

import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.parser.FlowerXmlTag;
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
    private static final String ELEMENT_ROSE = FlowerXmlTag.ROSE_FLOWER.getValue();
    private static final String ELEMENT_PION = FlowerXmlTag.PION_FLOWER.getValue();
    private final EnumSet<FlowerXmlTag> withText;
    private Set<Flower> flowers;
    private Flower currentFlower;
    private Rose currentRose;
    private Pion currentPion;
    private FlowerXmlTag currentXmlTag;


    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.FLOWER_NAME, FlowerXmlTag.ORIGIN);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_ROSE.equals(qName)) {
            currentRose = new Rose();
            currentRose.setId(attrs.getValue(0));
            currentRose.setInStoсk(Boolean.parseBoolean(attrs.getValue(1)));
            currentFlower = currentRose;

        } else if (ELEMENT_PION.equals(qName)) {
            currentPion = new Pion();
            currentRose.setId(attrs.getValue(0));
            currentRose.setInStoсk(Boolean.parseBoolean(attrs.getValue(1)));
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
        if (ELEMENT_ROSE.equals(qName)) {
            flowers.add(currentRose);
        } else if (ELEMENT_PION.equals(qName)) {
            flowers.add(currentPion);
        }
        System.out.println(flowers);
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case ID -> currentFlower.setId(data);
                case IN_STOCK -> currentFlower.setInStoсk(Boolean.parseBoolean(data));
                case FLOWER_NAME -> currentFlower.setFlowerName(data);
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
                    VisualParameters visualParameters = currentFlower.getVisualParameters();
                    visualParameters.setSize(Double.parseDouble(data));
                    currentFlower.setVisualParameters(visualParameters);
                }
                case INFLORESCENCE_COLOR -> {
                    VisualParameters visualParameters = currentFlower.getVisualParameters();
                    visualParameters.setInflorescenceColor(data);
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
                default -> {
                    logger.error("Unknown name " + currentXmlTag.name());
                    new ParsingXMLException("Data not exist");
                }
            }
        }
        currentXmlTag = null;
    }
}

