package com.nexai.task2.parser.builder;

import com.nexai.task2.parser.builder.AbstractFlowerBuilder;
import com.nexai.task2.parser.builder.impl.FlowerDomBuilder;
import com.nexai.task2.parser.builder.impl.FlowerStaxBuilder;
import com.nexai.task2.parser.builder.impl.FlowerSaxBuilder;
import com.nexai.task2.exception.ParsingXMLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FLowerBuilderFactory {
    private static final Logger logger = LogManager.getLogger();

    private static final FLowerBuilderFactory instance = new FLowerBuilderFactory();

    private FLowerBuilderFactory() {
    }

    public static FLowerBuilderFactory getInstance() {
        return instance;
    }

    public AbstractFlowerBuilder createFlowerBuilder(String type) throws ParsingXMLException {
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
            default -> {
                logger.info("Builder with this name" + typeToChoice + "doesn't exist!");
                throw new ParsingXMLException("Value isn't exist in enum" + typeToChoice.name());
            }
        }
    }
}


