package com.nexai.task2.util;

import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.validator.FlowerXmlValidator;

import java.net.URL;

public class ResourcePathUtil {

    public static String getResourcePath(String resourceName) throws ParsingXMLException {
        final int pathStartPosition = 6;
        ClassLoader loader = FlowerXmlValidator.class.getClassLoader();
        URL resource = loader.getResource(resourceName);
        if (resource == null) {
            throw new ParsingXMLException("Resource " + resourceName + " is not found");
        }
        return resource.toString().substring(pathStartPosition);
    }
}
