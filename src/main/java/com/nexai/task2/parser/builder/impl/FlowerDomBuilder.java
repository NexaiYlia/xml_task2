package com.nexai.task2.parser.builder.impl;

import com.nexai.task2.parser.builder.AbstractFlowerBuilder;
import com.nexai.task2.parser.FlowerXmlTag;
import com.nexai.task2.util.ResourcePathUtil;
import com.nexai.task2.parser.FlowerXmlAttribute;
import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.validator.FlowerXmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class FlowerDomBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;
    private Set<Flower> flowers;

    public FlowerDomBuilder() {
        flowers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Parser configuration error" + e);
        }
    }

    @Override
    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetFlowers(String fileName) throws ParsingXMLException {
        String schemaFileName = ResourcePathUtil.getResourcePath(AbstractFlowerBuilder.SCHEMA);
        if (FlowerXmlValidator.validateXml(fileName, schemaFileName)) {
            Document doc;
            try {
                doc = docBuilder.parse(fileName);
                Element root = doc.getDocumentElement();
                NodeList roseFlowersList = root.getElementsByTagName(FlowerXmlTag.ROSE_FLOWER.getName());
                NodeList pionFlowersList = root.getElementsByTagName(FlowerXmlTag.PION_FLOWER.getName());
                for (int i = 0; i < roseFlowersList.getLength(); i++) {
                    Element flowerElement = (Element) roseFlowersList.item(i);
                    Flower flower = buildRose(flowerElement);
                    flowers.add(flower);
                }
                for (int i = 0; i < pionFlowersList.getLength(); i++) {
                    Element flowerElement = (Element) pionFlowersList.item(i);
                    Flower flower = buildPion(flowerElement);
                    flowers.add(flower);
                }
            } catch (IOException | SAXException exception) {
                logger.error("Incorrect data" + exception);
            }
        } else {
            logger.info("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
            throw new ParsingXMLException("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
        }
    }

    private Flower buildRose(Element flowerElement) throws ParsingXMLException {
        Rose rose = new Rose();
        buildFlower(rose, flowerElement);
        String inStockAttr = FlowerXmlAttribute.IN_STOCK.getName();
        if (flowerElement.hasAttribute(inStockAttr)) {
            rose.setInStoсk(Boolean.parseBoolean(flowerElement.getAttribute(inStockAttr)));
        }
        rose.setSpikes(getElementBooleanContent(flowerElement, FlowerXmlTag.SPIKES.getName()));
        return rose;
    }

    private Flower buildPion(Element flowerElement) throws ParsingXMLException {
        Pion pion = new Pion();
        buildFlower(pion, flowerElement);
        String inStockAttr = FlowerXmlAttribute.IN_STOCK.getName();
        if (flowerElement.hasAttribute(inStockAttr)) {
            pion.setInStoсk(Boolean.parseBoolean(flowerElement.getAttribute(inStockAttr)));
        }
        pion.setNumberPeduncles(getElementIntContent(flowerElement, FlowerXmlTag.NUMBER_PEDUNCLES.getName()));
        return pion;
    }

    private void buildFlower(Flower flower, Element flowerElement) throws ParsingXMLException {
        flower.setId(flowerElement.getAttribute(FlowerXmlAttribute.ID.getName()));
        flower.setInStoсk(Boolean.parseBoolean(flowerElement.getAttribute((FlowerXmlAttribute.IN_STOCK.getName()))));
        flower.setFlowerName(getElementTextContent(flowerElement, FlowerXmlTag.FLOWER_NAME.getName()));
        flower.setDateOfPlanting(getElementYearMonthContent(flowerElement, FlowerXmlTag.DATE_OF_PLANTING.getName()));
        flower.setSoil(getElementSoil(flowerElement));
        flower.setOrigin(getElementTextContent(flowerElement, FlowerXmlTag.ORIGIN.getName()));
        flower.setMultiplying(getElementMultiplying(flowerElement));
        flower.setVisualParameters(buildVisualParameters(flowerElement));
        flower.setGrowingTips(buildGrowingTips(flowerElement));
    }

    private GrowingTips buildGrowingTips(Element element) {
        NodeList growingTipsList = element.getElementsByTagName(FlowerXmlTag.GROWING_TIPS.getName());
        Element growingTipsElement = (Element) growingTipsList.item(0);
        GrowingTips growingTips = new GrowingTips();
        growingTips.setMinTemperature(getElementIntContent(growingTipsElement, FlowerXmlTag.MIN_TEMPERATURE.getName()));
        growingTips.setLighting(getElementTextContent(growingTipsElement, FlowerXmlTag.LIGHTING.getName()));
        growingTips.setWatering(getElementTextContent(growingTipsElement, FlowerXmlTag.WATERING.getName()));
        return growingTips;
    }

    private VisualParameters buildVisualParameters(Element element) {
        NodeList visualParametersList = element.getElementsByTagName(FlowerXmlTag.VISUAL_PARAMETERS.getName());
        Element visualParametersElement = (Element) visualParametersList.item(0);
        VisualParameters visualParameters = new VisualParameters();
        visualParameters.setSize(getElementDoubleContent(visualParametersElement, FlowerXmlTag.SIZE.getName()));
        visualParameters.setInflorescenceColor(getElementTextContent(visualParametersElement, FlowerXmlTag.INFLORESCENCE_COLOR.getName()));
        return visualParameters;
    }

    private double getElementDoubleContent(Element element, String tagName) {
        String stringInt = getElementTextContent(element, tagName);
        return Double.parseDouble(stringInt);
    }

    private int getElementIntContent(Element element, String tagName) {
        String stringInt = getElementTextContent(element, tagName);
        return Integer.parseInt(stringInt);
    }

    private Soil getElementSoil(Element element) throws ParsingXMLException {
        String soil = getElementTextContent(element, FlowerXmlTag.SOIL.getName());
        return Soil.getSoil(soil);
    }

    private Multiplying getElementMultiplying(Element element) throws ParsingXMLException {
        String multiplying = getElementTextContent(element, FlowerXmlTag.MULTIPLYING.getName());
        return Multiplying.getMultiplying(multiplying);
    }

    private YearMonth getElementYearMonthContent(Element element, String tagName) {
        String yearMonthString = getElementTextContent(element, tagName);
        return YearMonth.parse(yearMonthString);
    }

    private boolean getElementBooleanContent(Element element, String tagName) {
        String booleanString = getElementTextContent(element, tagName);
        return Boolean.parseBoolean(booleanString);
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}


