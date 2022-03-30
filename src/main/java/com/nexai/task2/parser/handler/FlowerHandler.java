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
    private static final String VISUAL_PARAMETERS = FlowerXmlTag.VISUAL_PARAMETERS.getValue();
    private static final String GROWING_TIPS = FlowerXmlTag.GROWING_TIPS.getValue();
    private static final String MULTIPLYING = FlowerXmlTag.MULTIPLYING.getValue();
    private final EnumSet<FlowerXmlTag> withText;
    private Set<Flower> flowers;
    private Flower currentFlower;
    private Rose currentRose;
    private Pion currentPion;
    private VisualParameters currentVisualParameters;
    private GrowingTips currentGrowingTips;
    private Multiplying currentMultiplying;
    private FlowerXmlTag currentXmlTag;


    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.FLOWER_NAME, FlowerXmlTag.FEEDING_FREQUENCY);
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
                currentRose.setInStoсk(Boolean.parseBoolean(attrs.getValue(1)));
            }
            currentFlower = currentRose;

        } else if (ELEMENT_PION.equals(qName)) {
            currentPion = new Pion();
            currentPion.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                currentPion.setInStoсk(Boolean.parseBoolean(attrs.getValue(1)));
            }
            currentFlower = currentPion;
        } else if (VISUAL_PARAMETERS.equals(qName)) {
            currentVisualParameters = new VisualParameters();
        } else if (GROWING_TIPS.equals(qName)) {
            currentGrowingTips = new GrowingTips();
        } else {
            try {
                FlowerXmlTag temp = FlowerXmlTag.getXmlTag(qName);
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
            } catch (ParsingXMLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_ROSE.equals(qName)) {
            flowers.add(currentRose);
        } else if (ELEMENT_PION.equals(qName)) {
            flowers.add(currentPion);
        } else if (VISUAL_PARAMETERS.equals(qName)) {
            currentFlower.setVisualParameters(currentVisualParameters);
        } else if (GROWING_TIPS.equals(qName)) {
            currentFlower.setGrowingTips(currentGrowingTips);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case FLOWER_NAME -> {
                    currentFlower.setFlowerName(data);
                    break;
                }
                case DATE_OF_PLANTING -> {
                    currentFlower.setDateOfPlanting(YearMonth.parse(data));
                    break;
                }
                case SOIL -> {
                    try {
                        currentFlower.setSoil(Soil.getSoil(data));
                    } catch (ParsingXMLException e) {
                        logger.error("Soil not exist" + data + e);
                    }
                    break;
                }
                case ORIGIN -> {
                    currentFlower.setOrigin(data);
                    break;
                }
                case MULTIPLYING -> {
                    try {
                        currentFlower.setMultiplying(Multiplying.getMultiplying((data)));
                    } catch (ParsingXMLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case SPIKES -> {
                    currentRose = (Rose) currentFlower;
                    currentRose.setSpikes(Boolean.parseBoolean(data));
                    break;
                }
                case NUMBER_PEDUNCLES -> {
                    currentPion = (Pion) currentFlower;
                    currentPion.setNumberPeduncles(Integer.parseInt(data));
                    break;
                }
                case SIZE -> {
                    currentVisualParameters.setSize(Double.parseDouble(data));
                    currentFlower.setVisualParameters(currentVisualParameters);
                    break;
                }
                case INFLORESCENCE_COLOR -> {
                    currentVisualParameters.setInflorescenceColor(data);
                    currentFlower.setVisualParameters(currentVisualParameters);
                    break;
                }
                case MIN_TEMPERATURE -> {
                    currentGrowingTips.setMinTemperature(Integer.parseInt(data));
                    currentFlower.setGrowingTips(currentGrowingTips);
                    break;
                }
                case LIGHTING -> {
                    currentGrowingTips.setLighting(data);
                    currentFlower.setGrowingTips(currentGrowingTips);
                    break;
                }
                case WATERING -> {
                    currentGrowingTips.setWatering(data);
                    currentFlower.setGrowingTips(currentGrowingTips);
                    break;
                }
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
