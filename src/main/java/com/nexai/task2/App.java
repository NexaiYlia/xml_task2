package com.nexai.task2;

import com.nexai.task2.parser.handler.FlowerErrorHandler;
import com.nexai.task2.parser.handler.FlowerHandler;
import com.nexai.task2.parser.builder.impl.FlowerSaxBuilder;
import com.nexai.task2.parser.builder.impl.FlowerStaxBuilder;
import com.nexai.task2.exception.ParsingXMLException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws ParsingXMLException {

//        System.out.println(FlowerXMLValidator.validateXml("D:\\xml_task2\\src\\main\\resources\\flowers.xml", "D:\\xml_task2\\src\\main\\resources\\flowers.xsd"));
//        FlowerDomBuilder domBuilder = new FlowerDomBuilder();
//        domBuilder.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
//        System.out.println(domBuilder.getFlowers());

        FlowerStaxBuilder builder1 = new FlowerStaxBuilder();
        builder1.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
        System.out.println(builder1.getFlowers());

        FlowerSaxBuilder builder2 = new FlowerSaxBuilder();
        builder2.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
        System.out.println(builder2.getFlowers());
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new FlowerHandler());
            reader.setErrorHandler(new FlowerErrorHandler());
            reader.parse("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

//        FLowerJaxbBuilder builder3 = new FLowerJaxbBuilder();
//        builder3.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
//        System.out.println(builder3.getFlowers());
    }



