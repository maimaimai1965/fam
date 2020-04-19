package ua.mai.fam.model;

public class TogetherTypeTestData {

    public static final String MARRIAGE_TOGETHER_TYPE_CODE = "MARRIAGE";
    public static final String MARRIAGE_TOGETHER_TYPE_NAME = "Брак";

    public static final String togetherTypeCode01 = "TYPE_01";
    public static final String togetherTypeName01 = "тип together 01";

    public static final String togetherTypeCode02 = "TYPE_02";
    public static final String togetherTypeName02 = "тип together 02";

    public static final TogetherType MARRIAGE_TOGETHER_TYPE =
        new TogetherType(MARRIAGE_TOGETHER_TYPE_CODE, MARRIAGE_TOGETHER_TYPE_NAME);


    public static TogetherType getNewTogetherType01() {
        return getTogetherType01(togetherTypeCode01);
    }
    public static TogetherType getTogetherType01(String code) {
        return new TogetherType(code, togetherTypeName01);
    }

    public static TogetherType getNewTogetherType02() {
        return getTogetherType02(togetherTypeCode02);
    }
    public static TogetherType getTogetherType02(String code) {
        return new TogetherType(code, togetherTypeName02);
    }

}
