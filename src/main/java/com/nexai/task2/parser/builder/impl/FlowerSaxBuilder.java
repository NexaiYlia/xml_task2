package com.nexai.task2.parser.builder.impl;

import com.nexai.task2.parser.builder.AbstractFlowerBuilder;
import com.nexai.task2.parser.handler.FlowerErrorHandler;
import com.nexai.task2.parser.handler.FlowerHandler;
import com.nexai.task2.entity.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;


public class FlowerSaxBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final FlowerHandler handler = new FlowerHandler();
    private final FlowerErrorHandler errorHandler = new FlowerErrorHandler();
    private XMLReader reader;
    private Set<Flower> flowers;

    public FlowerSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();

        } catch (ParserConfigurationException | SAXException exception) {
            logger.error("Parsing can't be done" + exception);
        }
        reader.setErrorHandler(errorHandler);
        reader.setContentHandler(handler);
    }

    public void buildSetFlowers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException exception) {
            logger.error("Parsing can't be done" + exception);
        }
        flowers = handler.getFlowers();
    }
}