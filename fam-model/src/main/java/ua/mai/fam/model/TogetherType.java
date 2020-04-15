package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.mai.fam.util.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "TOGETHER_TYPE", schema = "public", catalog = "fam")
public class TogetherType implements HasId<String> {

    @Id
    @Column(name = "code", nullable = false, length = 30)
    private String code;

    @Basic
    @NotNull
    @Column(name = "name", length = 100)
    private String name;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TogetherType that = (TogetherType) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }


    /* */
    @Override
    public String getTableName() {
        return "TOGETHER_TYPE";
    }
    @Override
    public String getId() {
        return code;
    }


    public TogetherType() {
    }

    public TogetherType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TogetherType{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
    }

}
