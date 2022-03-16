package com.nexai.task2.builder.impl;

import com.nexai.task2.util.ResourcePathUtil;
import com.nexai.task2.builder.AbstractFlowerBuilder;
import com.nexai.task2.entity.Flowers;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.validator.FlowerXMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FLowerJaxbBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private final Unmarshaller unmarshaller;

    public FLowerJaxbBuilder() throws ParsingXMLException {
        try {
            JAXBContext context = JAXBContext.newInstance(Flowers.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException exception) {
            logger.error("Builder is not created", exception);
            throw new ParsingXMLException(exception);
        }
    }

    @Override
    public void buildSetFlowers(String fileName) throws ParsingXMLException {
        String schemaFileName = ResourcePathUtil.getResourcePath(AbstractFlowerBuilder.SCHEMA);
        if (FlowerXMLValidator.validateXml(fileName, schemaFileName)) {
            try {
                InputStream fileInputStream = new FileInputStream(fileName);
                Flowers flowersObject = (Flowers) unmarshaller.unmarshal(fileInputStream);
                flowers = flowersObject.getFlowers();
                logger.info("Flowers is build. " + flowers);
            } catch (FileNotFoundException | JAXBException exception) {
                logger.error("Exception when build Set of candies", exception);
                throw new ParsingXMLException(exception);
            }
        } else {
            logger.info("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
            throw new ParsingXMLException("File '" + fileName + "' does not match schema '" + schemaFileName + "'");
        }
    }
}


