package ua.mai.fam.model;

import ua.mai.fam.dto.TogetherTypeDto;

public class TogetherTypeTestData {

    public static final String MARRIAGE_TOGETHER_TYPE_CODE = "MARRIAGE";
    public static final String MARRIAGE_TOGETHER_TYPE_NAME = "Брак";

    public static final TogetherType MARRIAGE_TOGETHER_TYPE =
        new TogetherType(MARRIAGE_TOGETHER_TYPE_CODE, MARRIAGE_TOGETHER_TYPE_NAME);


    public static final String code01 = "MARRIAGE1_TEST";
    public static final String name01 = "брак1 TEST";

    public static final String code02 = "MARRIAGE2_TEST";
    public static final String name02 = "брак2 TEST";

    public static TogetherType getTogetherType01() {
        return new TogetherType(code01, name01);
    }

    public static TogetherType getTogetherType02() {
        return new TogetherType(code02, name02);
    }

}
