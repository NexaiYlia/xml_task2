package com.nexai.task2.builder;

import com.nexai.task2.builder.impl.FlowerDomBuilder;
import com.nexai.task2.builder.impl.FlowerStaxBuilder;
import com.nexai.task2.builder.impl.FlowerSaxBuilder;
import com.nexai.task2.exception.ParsingXMLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FLowerBuilderFactory {
    private static final Logger logger = LogManager.getLogger();

    private enum TypeParser {
        SAX,
        STAX,
        DOM,
        JAXB
    }

    private FLowerBuilderFactory() {
    }

    public static AbstractFlowerBuilder createFlowerBuilder(String type) throws ParsingXMLException {
        TypeParser typeToChoice = TypeParser.valueOf(type.toUpperCase());
        switch (typeToChoice) {
            case DOM -> {
                logger.info("Created new FlowerDomBuilder");
                return new FlowerDomBuilder();
            }
            case STAX -> {
                logger.info("Created new FlowerStaxBuilder");
                return new FlowerStaxBuilder();
            }
            case SAX -> {
                logger.info("Created new FlowerSaxBuilder");
                return new FlowerSaxBuilder();
            }
            case JAXB -> {
                logger.info("Created new FlowerJaxbBuilder");
                return new FlowerSaxBuilder();
            }
            default -> throw new ParsingXMLException("Value isn't exist in enum" + typeToChoice.name());
        }
    }
}


