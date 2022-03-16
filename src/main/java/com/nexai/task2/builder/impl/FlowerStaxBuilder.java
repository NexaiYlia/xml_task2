package com.nexai.task2.builder.impl;

import com.nexai.task2.ResourcePathUtil;
import com.nexai.task2.builder.AbstractFlowerBuilder;
import com.nexai.task2.builder.handler.FlowerXmlTag;
import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.validator.FlowerXMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;


public class FlowerStaxBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    private XMLInputFactory inputFactory;
    private Set<Flower> flowers;

    public FlowerStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        flowers = new HashSet<Flower>();
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetFlowers(String fileName) throws ParsingXMLException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerXmlTag.ROSE.getValue())) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    } else if (name.equals(FlowerXmlTag.PION.getValue())) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException exception) {
            logger.error("Error of build", exception);
            throw new ParsingXMLException(exception);
        } catch (IOException exception) {
            logger.error("Error of build", exception);
            throw new ParsingXMLException(exception);
        }
    }


    private Flower buildFlower(XMLStreamReader reader) throws
            XMLStreamException, ParsingXMLException {
        Flower flower = new Flower();
        flower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.getValue()));
        flower.setInStok(Boolean.parseBoolean(reader.getAttributeValue(null, (FlowerXmlTag.IN_STOCK.getValue()))));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (tag) {
                        case NAME -> {
                            flower.setName(getXMLText(reader));
                        }
                        case DATE_OF_PLANTING -> {
                            flower.setDateOfPlanting(YearMonth.parse(getXMLText(reader)));

                        }
                        case SOIL -> {
                            flower.setSoil(Soil.getSoil(getXMLText(reader)));

                        }
                        case ORIGIN -> {
                            flower.setOrigin(getXMLText(reader));

                        }
                        case MULTIPLYING -> {
                            flower.setMultiplying(Multiplying.getMultiplying(getXMLText(reader)));

                        }
                        case SPIKES -> {
                            Rose rose = (Rose) flower;
                            rose.setSpikes(Boolean.parseBoolean(getXMLText(reader)));

                        }
                        case NUMBER_PEDUNCLES -> {
                            Pion pion = (Pion) flower;
                            pion.setNumberPeduncles(Integer.parseInt(getXMLText(reader)));

                        }
                        case SIZE -> {
                            VisualParameters visualParameters = flower.getVisualParameters();
                            visualParameters.setSize(Double.parseDouble(getXMLText(reader)));
                            flower.setVisualParameters(visualParameters);

                        }
                        case INFLORESCENCE_COLOR -> {
                            VisualParameters visualParameters = flower.getVisualParameters();
                            visualParameters.setInflorescenceColor(getXMLText(reader));
                            flower.setVisualParameters(visualParameters);

                        }

                        case MIN_TEMPERATURE -> {
                            GrowingTips growingTips = flower.getGrowingTips();
                            growingTips.setMinTemperature(Integer.parseInt(getXMLText(reader)));
                            flower.setGrowingTips(growingTips);

                        }
                        case LIGHTING -> {
                            GrowingTips growingTips = flower.getGrowingTips();
                            growingTips.setLighting(getXMLText(reader));
                            flower.setGrowingTips(growingTips);

                        }
                        case WATERING -> {
                            GrowingTips growingTips = flower.getGrowingTips();
                            growingTips.setWatering(getXMLText(reader));
                            flower.setGrowingTips(growingTips);

                        }
                        case GROWING_TIPS -> {
                            flower.setGrowingTips(getXMLGrowingTips(reader));

                        }
                        case VISUAL_PARAMETERS -> {
                            flower.setVisualParameters(getXMLVisualParameters(reader));

                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.getXmlTag(name);
                    if (FlowerXmlTag.ROSE == tag || FlowerXmlTag.PION == tag) {
                        return flower;
                    }
                }
            }
        }
        throw new ParsingXMLException("End tag of " + flower.getClass().getSimpleName() + " is not found.");
    }


    private GrowingTips getXMLGrowingTips(XMLStreamReader reader) throws
            XMLStreamException, ParsingXMLException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.getXmlTag(name);
                    switch (tag) {
                        case WATERING -> growingTips.setWatering(getXMLText(reader));
                        case LIGHTING -> growingTips.setLighting((getXMLText(reader)));
                        case MIN_TEMPERATURE -> growingTips.setMinTemperature(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerXmlTag.GROWING_TIPS == FlowerXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        return growingTips;
                    }
            }
        }
        throw new XMLStreamException("End tag is not found");
    }


    private VisualParameters getXMLVisualParameters(XMLStreamReader reader) throws
            XMLStreamException, ParsingXMLException {
        VisualParameters visualParameters = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.getXmlTag(name);
                    switch (tag) {
                        case SIZE -> visualParameters.setSize(Double.parseDouble(getXMLText(reader)));
                        case INFLORESCENCE_COLOR -> visualParameters.setInflorescenceColor((getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (FlowerXmlTag.VISUAL_PARAMETERS == FlowerXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        return visualParameters;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag of Ingredient is not found");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}





