package com.nexai.task2.builder.impl;

import com.nexai.task2.builder.AbstractFlowerBuilder;
import com.nexai.task2.entity.*;
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
    public void buildSetFlowers(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList roseFlowersList = root.getElementsByTagName("rose");
            NodeList pionFlowersList = root.getElementsByTagName("pion");
            NodeList rhododendronFlowersList = root.getElementsByTagName("rhododendron");
            NodeList orchidFlowersList = root.getElementsByTagName("orchid");
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
            for (int i = 0; i < rhododendronFlowersList.getLength(); i++) {
                Element flowerElement = (Element) roseFlowersList.item(i);
                Flower flower = buildRhododendron(flowerElement);
                flowers.add(flower);
            }
            for (int i = 0; i < orchidFlowersList.getLength(); i++) {
                Element flowerElement = (Element) roseFlowersList.item(i);
                Flower flower = buildOrchid(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException | SAXException exception) {
            logger.error("Uncorrect data" + exception);
        }
    }

    private Rose buildRose(Element flowerElement) {
        Rose rose = new Rose();
        buildFlower(rose, flowerElement);
        if (flowerElement.hasAttribute("id")) {
            rose.setId(flowerElement.getAttribute("id"));
        }
        rose.setSpikes(getElementBooleanContent(flowerElement, "spikes"));
        return rose;
    }

    private Pion buildPion(Element flowerElement) {
        Pion pion = new Pion();
        buildFlower(pion, flowerElement);
        if (flowerElement.hasAttribute("id")) {
            pion.setId(flowerElement.getAttribute("id"));
        }
        pion.setNumberPeduncles(getElementIntContent(flowerElement, "number-peduncles"));
        return pion;
    }

    private Rhododendron buildRhododendron(Element flowerElement) {
        Rhododendron rhododendron = new Rhododendron();
        buildFlower(rhododendron, flowerElement);
        if (flowerElement.hasAttribute("id")) {
            rhododendron.setId(flowerElement.getAttribute("id"));
        }
        rhododendron.setStemThickness(getElementIntContent(flowerElement, "stem-thickness"));
        return rhododendron;
    }

    private Orchid buildOrchid(Element flowerElement) {
        Orchid orchid = new Orchid();
        buildFlower(orchid, flowerElement);
        if (flowerElement.hasAttribute("id")) {
            orchid.setId(flowerElement.getAttribute("id"));
        }
        orchid.setFeedingFrequency(getElementTextContent(flowerElement, "feeding-frequency"));
        return orchid;
    }


    private void buildFlower(Flower flower, Element flowerElement) {
        flower.setId(flowerElement.getAttribute("id"));
        flower.setName(getElementTextContent(flowerElement, "name"));
        flower.setDateOfPlanting(getElementYearMonthContent(flowerElement, "date-of-planting"));
        flower.setSoil(getElementSoilValue(flowerElement));
        flower.setOrigin(getElementTextContent(flowerElement, "origin"));
        flower.setMultiplying(getElementMultiplyingValue(flowerElement));
        flower.setVisualParameters(buildVisualParameters(flowerElement));
        flower.setGrowingTips(buildGrowingTips(flowerElement));
    }


    private GrowingTips buildGrowingTips(Element element) {
        NodeList growingTipsList = element.getElementsByTagName("growing-tips");
        Element growingTipsElement = (Element) growingTipsList.item(0);
        GrowingTips growingTips = new GrowingTips();
        growingTips.setMinTemperature(getElementIntContent(growingTipsElement, "min-temperature"));
        growingTips.setLighting(getElementTextContent(growingTipsElement, "lighting"));
        growingTips.getWatering(getElementTextContent(growingTipsElement, "watering"));
        return growingTips;
    }

    private VisualParameters buildVisualParameters(Element element) {
        NodeList visualParametersList = element.getElementsByTagName("growing-tips");
        Element visualParametersElement = (Element) visualParametersList.item(0);
        VisualParameters visualParameters = new VisualParameters();
        visualParameters.getSize(getElementIntContent(visualParametersElement, "size"));
        visualParameters.getInflorescence_color(getElementTextContent(visualParametersElement, "inflorescence-color"));

        return visualParameters;
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

    private Soil getElementSoilValue(Element element) {
        String soil = getElementTextContent(element, "soil");
        return Soil.getSoil(soil);
    }

    private Multiplying getElementMultiplyingValue(Element element) {
        String multiplying = getElementTextContent(element, "multiplying");
        return Multiplying.getMultiplying(multiplying);
    }

    private YearMonth getElementYearMonthContent(Element element, String tagName) {
        String yearMonthString = getElementTextContent(element, tagName);
        return YearMonth.parse(yearMonthString);
    }

    private boolean getElementBooleanContent(Element candyElement, String tagName) {
        String booleanString = getElementTextContent(candyElement, tagName);
        return Boolean.parseBoolean(booleanString);
    }
}
