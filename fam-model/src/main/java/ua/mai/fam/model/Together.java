package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.mai.fam.model.person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "TOGETHER")
public class Together {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    //--for JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_together")
    @SequenceGenerator(name="seq_together", sequenceName="SEQ_TOGETHER", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "together_type_code")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private TogetherType togetherType;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "person1_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Person person1;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "person2_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Person person2;

    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    @Basic
    @Column(name = "start_date")
    private LocalDate startDate;

    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    @Basic
    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Basic
    @Column(name = "description", length = 500)
    private String description;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public TogetherType getTogetherType() {
        return togetherType;
    }
    public void setTogetherType(TogetherType togetherType) {
        this.togetherType = togetherType;
    }

    public Person getPerson1() {
        return person1;
    }
    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }
    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Together together = (Together) o;

        if (id != together.id) return false;

        //togetherType проверяем по коду
        if (togetherType != null ? !togetherType.getCode().equals(together.togetherType)
                                 : together.togetherType != null) return false;
        //person1 проверяем по id
        if (person1 != null ? !person1.getId().equals(together.person1.getId())
                            : together.person1 != null) return false;
        //person2 проверяем по id
        if (person2 != null ? !person2.getId().equals(together.person2.getId())
                            : together.person2 != null) return false;

        if (startDate != null ? !startDate.equals(together.startDate) : together.startDate != null) return false;
        if (finishDate != null ? !finishDate.equals(together.finishDate) : together.finishDate != null) return false;
        return description != null ? description.equals(together.description) : together.description == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (togetherType != null ? togetherType.hashCode() : 0);
//        result = 31 * result + (person1 != null ? person1.hashCode() : 0);
//        result = 31 * result + (person2 != null ? person2.hashCode() : 0);
//        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
//        result = 31 * result + (finishDate != null ? finishDate.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        return result;
    }

    public Together() {
    }

    public Together(long id, TogetherType togetherType, Person person1, Person person2, LocalDate startDate,
                    LocalDate finishDate, String description) {
        this.id = id;
        this.togetherType = togetherType;
        this.person1 = person1;
        this.person2 = person2;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

}
