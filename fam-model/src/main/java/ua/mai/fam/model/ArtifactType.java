package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "artifact_type")
public class ArtifactType {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    //--for Data JPA
    @Id
    @Column(name = "code", nullable = false, length = 30)
    private String code;

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;


    public String getCode() {
        return code;
    }
//    public void setCode(String code) {
//        this.code = code;
//    }

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
        ArtifactType that = (ArtifactType) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
