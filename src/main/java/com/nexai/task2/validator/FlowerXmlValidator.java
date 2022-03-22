package com.nexai.task2.validator;

import com.nexai.task2.parser.handler.FlowerErrorHandler;
import com.nexai.task2.exception.ParsingXMLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;


public class FlowerXmlValidator {
    private static final Logger logger = LogManager.getLogger();
    private static FlowerXmlValidator instance;

    private FlowerXmlValidator() {
    }

    public static FlowerXmlValidator getInstance() {
        if (instance == null) {
            instance = new FlowerXmlValidator();
        }
        return instance;
    }

    public static boolean validateXml(String xmlFileName, String schemaFileName) throws ParsingXMLException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaFileName);

            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFileName);
            validator.validate(source);
            validator.setErrorHandler(new FlowerErrorHandler());
            logger.info("File '" + xmlFileName + "' matches schema '" + schemaFileName + "'");
            return true;
        } catch (SAXException e) {
            logger.warn("XML is invalid" + e);
            return false;
        } catch (IOException e) {
            logger.error("Can`t read file" + e);
            throw new ParsingXMLException(e);
        }
    }
}



