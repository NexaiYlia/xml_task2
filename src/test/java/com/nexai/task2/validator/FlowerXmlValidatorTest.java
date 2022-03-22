package com.nexai.task2.validator;

import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.util.ResourcePathUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlowerXmlValidatorTest {

    @Test
    public void testIsValidXML() throws ParsingXMLException {
        String fileNameXml = ResourcePathUtil.getResourcePath("flowers.xml");
        String fileNameSchema = ResourcePathUtil.getResourcePath("flowers.xsd");
        boolean expected = true;
        boolean actual = FlowerXMLValidator.validateXml(fileNameXml, fileNameSchema);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsNotValidXML() throws ParsingXMLException {
        String fileNameXml = ResourcePathUtil.getResourcePath("flowers2.xml");
        String fileNameSchema = ResourcePathUtil.getResourcePath("flowers.xsd");
        boolean expected = false;
        boolean actual = FlowerXMLValidator.validateXml(fileNameXml, fileNameSchema);
        Assert.assertEquals(expected, actual);
    }
}
