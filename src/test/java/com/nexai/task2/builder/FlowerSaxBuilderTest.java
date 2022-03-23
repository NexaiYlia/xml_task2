package com.nexai.task2.builder;

import com.nexai.task2.entity.*;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.parser.builder.AbstractFlowerBuilder;
import com.nexai.task2.parser.builder.FLowerBuilderFactory;
import com.nexai.task2.util.ResourcePathUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import static org.testng.AssertJUnit.fail;

public class FlowerSaxBuilderTest {
    private static final String FILE_NAME = "flowers.xml";
    private Set<Flower> expected;

    public void setUp() {
        expected = new HashSet<>();
        VisualParameters visualParametersRose = new VisualParameters(1.5, "red");
        GrowingTips growingTipsRose = new GrowingTips(-10, "light - loving", "twice a week");
        Rose rose = new Rose("f51", false, "Kazanlik Rose", YearMonth.of(2020, 5), Soil.GROUND, "India", visualParametersRose, growingTipsRose, Multiplying.CUTTINGS, true);

        VisualParameters visualParametersPion = new VisualParameters(0.8, "white");
        GrowingTips growingTipsPion = new GrowingTips(-12, "light - loving", "twice a week");
        Pion pion = new Pion("f55", true, "Cattleya", YearMonth.of(2021, 8), Soil.TREE_BARK, "Netherlands", visualParametersPion, growingTipsPion, Multiplying.CUTTINGS, 8);
        expected.add(rose);
        expected.add(pion);
    }

    @Test
    public void flowerStaxBuilderTest() {
        AbstractFlowerBuilder builder;
        setUp();
        String fileName;
        Set<Flower> actual = null;
        try {
            fileName = ResourcePathUtil.getResourcePath(FILE_NAME);
            builder = FLowerBuilderFactory.getInstance().createFlowerBuilder("SAX");
            builder.buildSetFlowers(fileName);
            actual = builder.getFlowers();
        } catch (ParsingXMLException exception) {
            fail("Test failed. " + exception.getMessage());
        }
        Assert.assertEquals(expected.containsAll(actual), actual.containsAll(expected));

    }
}
