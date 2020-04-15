package ua.mai.fam.model;

import ua.mai.fam.model.person.Person;
import ua.mai.fam.model.person.PersonBuilder;

public class TogetherTypeTestData {

    public static final String MARRIAGE_TOGETHER_TYPE_CODE = "MARRIAGE";
    public static final String MARRIAGE_TOGETHER_TYPE_NAME = "Брак";

    public static final String togetherTypeCode01 = "TYPE_01";
    public static final String togetherTypeName01 = "тип together 01";

    public static final String togetherTypeCode02 = "TYPE_02";
    public static final String togetherTypeName02 = "тип together 02";

    public static final TogetherType MARRIAGE_TOGETHER_TYPE =
        new TogetherType(MARRIAGE_TOGETHER_TYPE_CODE, MARRIAGE_TOGETHER_TYPE_NAME);

    public static TogetherType getTogetherType01() {
        return new TogetherType(togetherTypeCode01, togetherTypeName01);
    }

    public static TogetherType getTogetherType02() {
        return new TogetherType(togetherTypeCode02, togetherTypeName02);
    }

}
