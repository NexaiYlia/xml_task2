package com.nexai.task2.builder.impl;

import com.nexai.task2.builder.AbstractFlowerBuilder;
import com.nexai.task2.builder.handler.FlowerErrorHandler;
import com.nexai.task2.builder.handler.FlowerHandler;
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

public class FlowersSaxBuilder extends AbstractFlowerBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Flower> flowers;
    private final FlowerHandler handler = new FlowerHandler();
    private XMLReader reader;

    public FlowersSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException exception) {
            logger.error("Parsing can't be done" + exception);
        }
        reader.setErrorHandler(new FlowerErrorHandler());
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
