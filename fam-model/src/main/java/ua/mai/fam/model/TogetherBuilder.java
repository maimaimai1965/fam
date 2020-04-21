package ua.mai.fam.model;

import java.time.LocalDate;

public class TogetherBuilder {

    private long id;
    private TogetherType togetherType;
    private Person person1;
    private Person person2;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String description;

    public TogetherBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public TogetherBuilder setTogetherType(TogetherType togetherType) {
        this.togetherType = togetherType;
        return this;
    }

    public TogetherBuilder setPerson1(Person person1) {
        this.person1 = person1;
        return this;
    }

    public TogetherBuilder setPerson2(Person person2) {
        this.person2 = person2;
        return this;
    }

    public TogetherBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public TogetherBuilder setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    public TogetherBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Together createTogether() {
        return new Together(id, togetherType, person1, person2, startDate, finishDate, description);
    }

}