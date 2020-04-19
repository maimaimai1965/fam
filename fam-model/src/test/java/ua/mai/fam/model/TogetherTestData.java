package ua.mai.fam.model;

import java.time.LocalDate;

public class TogetherTestData {

    public static final LocalDate startDate01 = LocalDate.of(2001, 3, 26);
    public static final LocalDate finishDate01 = LocalDate.of(2010, 11, 28);

    public static final LocalDate startDate02 = null;
    public static final LocalDate finishDate02 = null;

    public static final Together TOGETHER_01 = getNewTogether01();
    public static final Together TOGETHER_02 = getNewTogether02();


    public static Together getNewTogether01() {
        return new TogetherBuilder()
            .setTogetherType(TogetherTypeTestData.MARRIAGE_TOGETHER_TYPE)
            .setPerson1(PersonTestData.getNewPerson01())
            .setPerson2(PersonTestData.getNewPerson03())
            .setStartDate(startDate01)
            .setFinishDate(finishDate01)
            .createTogether();
    }

    public static Together getNewTogether02() {
        return new TogetherBuilder()
            .setTogetherType(TogetherTypeTestData.MARRIAGE_TOGETHER_TYPE)
            .setPerson1(PersonTestData.getNewPerson02())
            .setPerson2(PersonTestData.getNewPerson03())
            .setStartDate(startDate02)
            .setFinishDate(finishDate02)
            .createTogether();
    }

}
