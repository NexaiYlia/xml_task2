package com.nexai.task2;

import com.nexai.task2.builder.impl.FlowerDomBuilder;

import com.nexai.task2.builder.impl.FlowerSaxBuilder;
import com.nexai.task2.builder.impl.FlowerStaxBuilder;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.validator.FlowerXMLValidator;

public class App {
    public static void main(String[] args) throws ParsingXMLException {

//        System.out.println(FlowerXMLValidator.validateXml("D:\\xml_task2\\src\\main\\resources\\flowers.xml", "D:\\xml_task2\\src\\main\\resources\\flowers.xsd"));
//        FlowerDomBuilder domBuilder = new FlowerDomBuilder();
//        domBuilder.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
//        System.out.println(domBuilder.getFlowers());

//        FlowerStaxBuilder builder = new FlowerStaxBuilder();
//        builder.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
//        System.out.println(builder.getFlowers());

        FlowerSaxBuilder builder = new FlowerSaxBuilder();
        builder.buildSetFlowers("D:\\xml_task2\\src\\main\\resources\\flowers.xml");
        System.out.println(builder.getFlowers());
    }

}
