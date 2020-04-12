package ua.mai.fam.model;

import ua.mai.fam.model.person.Person;
import ua.mai.fam.model.person.PersonBuilder;

public class TogetherTypeTestData {

    public static final String MARRIAGE_TOGETHER_TYPE_CODE = "MARRIAGE";
    public static final String MARRIAGE_TOGETHER_TYPE_NAME = "Брак";

    public static final TogetherType MARRIAGE_TOGETHER_TYPE =
        new TogetherType(MARRIAGE_TOGETHER_TYPE_CODE, MARRIAGE_TOGETHER_TYPE_NAME);

}
