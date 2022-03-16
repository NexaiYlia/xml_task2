package com.nexai.task2.builder.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;


public class FlowerErrorHandler implements ErrorHandler {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) {
        logger.warn(getLineColumnNumber(exception) + "-" + exception.getMessage());

    }

    @Override
    public void error(SAXParseException exception) {
        logger.error(getLineColumnNumber(exception) + "-" + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) {
        logger.fatal(getLineColumnNumber(exception) + "-" + exception.getMessage());
    }

    private String getLineColumnNumber(SAXParseException exception) {
        return exception.getLineNumber() + ":" + exception.getColumnNumber();
    }
}
