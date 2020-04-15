package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "BOX_TYPE")
public class BoxType {

    @Id
    @Column(name = "code", nullable = false, length = 30)
    private String code;

    @Basic
    @NotNull
    @Column(name = "description", length = 100)
    private String description;

    @Basic
    @NotNull
    @Column(name = "is_bin", length = 1)
    private String isBin;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsBin() {
        return isBin;
    }
    public void setIsBin(String isBin) {
        this.isBin = isBin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoxType boxType = (BoxType) o;
        return Objects.equals(code, boxType.code) &&
            Objects.equals(description, boxType.description) &&
            Objects.equals(isBin, boxType.isBin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, isBin);
    }
}
