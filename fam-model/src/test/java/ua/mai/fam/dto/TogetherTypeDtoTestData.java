package ua.mai.fam.dto;

import ua.mai.fam.model.TogetherTypeTestData;

public class TogetherTypeDtoTestData {

    public static final String code01 = TogetherTypeTestData.code01;
    public static final String name01 = TogetherTypeTestData.name01;

    public static final String code02 = TogetherTypeTestData.code01;
    public static final String name02 = TogetherTypeTestData.name02;


    public static TogetherTypeDto getTogetherTypeDto01() {
        return new TogetherTypeDto(code01, name01);
    }

    public static TogetherTypeDto getTogetherTypeDto02() {
        return new TogetherTypeDto(code02, name02);
    }

}
