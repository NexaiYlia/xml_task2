package com.nexai.task2.builder.impl;

import com.nexai.task2.ResourcePathUtil;
import com.nexai.task2.builder.AbstractFlowerBuilder;
import com.nexai.task2.builder.handler.FlowerXmlAttribute;
import com.nexai.task2.builder.handler.FlowerXmlTag;
import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.validator.FlowerXMLValidator;
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

public class FlowerDomBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public FlowerDomBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Parser configuration error" + e);
        }
    }

    @Override
    public void buildSetFlowers(String fileName) throws ParsingXMLException {
        String schemaFileName = ResourcePathUtil.getResourcePath(AbstractFlowerBuilder.SCHEMA);
        if (FlowerXMLValidator.validateXml(fileName, schemaFileName)) {
            Document doc;
            try {
                doc = docBuilder.parse(fileName);
                Element root = doc.getDocumentElement();
                NodeList roseFlowersList = root.getElementsByTagName(FlowerXmlTag.ROSE.getName());
                NodeList pionFlowersList = root.getElementsByTagName(FlowerXmlTag.PION.getName());
                for (int i = 0; i < roseFlowersList.getLength(); i++) {
                    Element flowerElement = (Element) roseFlowersList.item(i);
                    Flower flower = buildRose(flowerElement);
                    flowers.add(flower);
                }
                for (int i = 0; i < pionFlowersList.getLength(); i++) {
                    Element flowerElement = (Element) roseFlowersList.item(i);
                    Flower flower = buildPion(flowerElement);
                    flowers.add(flower);
                }
            } catch (IOException | SAXException exception) {
                logger.error("Incorrect data" + exception);
            }
        }
    }


    private Rose buildRose(Element flowerElement) throws ParsingXMLException {
        Rose rose = new Rose();
        buildFlower(rose, flowerElement);
        String inStockAttr = FlowerXmlAttribute.IN_STOCK.getName();
        if (flowerElement.hasAttribute(inStockAttr)) {
            rose.setInStok(Boolean.parseBoolean(flowerElement.getAttribute(inStockAttr)));
        }
        rose.setSpikes(getElementBooleanContent(flowerElement, FlowerXmlTag.SPIKES.getName()));
        return rose;
    }

    private Pion buildPion(Element flowerElement) throws ParsingXMLException {
        Pion pion = new Pion();
        buildFlower(pion, flowerElement);
        pion.setNumberPeduncles(getElementIntContent(flowerElement, FlowerXmlTag.NUMBER_PEDUNCLES.getName()));
        return pion;
    }


    private void buildFlower(Flower flower, Element flowerElement) throws ParsingXMLException {
        //flower.setId(flowerElement.getAttribute(FlowerXmlTag.ID.getName()));
        //flower.setInStok(getElementBooleanContent(flowerElement, FlowerXmlTag.IN_STOCK.getName()));
        flower.setName(getElementTextContent(flowerElement, FlowerXmlTag.NAME.getName()));
        flower.setDateOfPlanting(getElementYearMonthContent(flowerElement, FlowerXmlTag.DATE_OF_PLANTING.getName()));
        flower.setSoil(getElementSoilValue(flowerElement));
        flower.setOrigin(getElementTextContent(flowerElement, FlowerXmlTag.ORIGIN.getName()));
        flower.setMultiplying(getElementMultiplyingValue(flowerElement));
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

    private String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private Soil getElementSoilValue(Element element) throws ParsingXMLException {
        String soil = getElementTextContent(element, FlowerXmlTag.SOIL.getName());
        return Soil.getSoil(soil);
    }

    private Multiplying getElementMultiplyingValue(Element element) throws ParsingXMLException {
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
}
