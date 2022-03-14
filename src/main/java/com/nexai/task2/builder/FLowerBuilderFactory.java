package com.nexai.task2.builder;

import com.nexai.task2.builder.impl.FlowerDomBuilder;
import com.nexai.task2.builder.impl.FlowerStaxBuilder;
import com.nexai.task2.builder.impl.FlowersSaxBuilder;
import com.nexai.task2.exception.ParsingXMLException;
import com.nexai.task2.parser.TypeParser;

public class FLowerBuilderFactory {
    private TypeParser typeParser;

    public FLowerBuilderFactory(TypeParser typeParser) {
        this.typeParser = typeParser;
    }


    public static AbstractFlowerBuilder createFlowerBuilder(String type) throws ParsingXMLException {
        TypeParser typeToChoice = TypeParser.valueOf(type.toUpperCase());
        switch (typeToChoice) {
            case DOM -> {
                return new FlowerDomBuilder();
            }
            case STAX -> {
                return new FlowerStaxBuilder();
            }
            case SAX -> {
                return new FlowersSaxBuilder();
            }
            default -> throw new ParsingXMLException("Value isn't exist in enum" + typeToChoice);
        }
    }
}
