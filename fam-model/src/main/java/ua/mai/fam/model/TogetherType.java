package ua.mai.fam.model;

import ua.mai.fam.dto.TogetherTypeDto;
import ua.mai.fam.util.HasId;
import ua.mai.fam.util.ToDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

//--For JPA
@Entity
@Table(name = "TOGETHER_TYPE", schema = "public", catalog = "fam")
public class TogetherType implements HasId<String>, ToDto<TogetherTypeDto> {

    @Id
    @Column(name = "CODE", nullable = false, length = 30)
    private String code;

    @Basic
    @NotNull
    @Column(name = "NAME", length = 100)
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


    public TogetherType() {}

    public TogetherType(String code, String name) {
        this.code = code;
        this.name = name;
    }


    @Override
    public String getId() {
        return code;
    }

    @Override
    public String getTableName() {
        return "TOGETHER_TYPE";
    }

    @Override
    public TogetherTypeDto toDto() {
        return new TogetherTypeDto(code, name);
    }

    public static List<TogetherTypeDto> toDtos(Collection<TogetherType> entities)  {
        return (List<TogetherTypeDto>) ToDto.toDtos(entities);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TogetherType)) return false;

        TogetherType that = (TogetherType) o;

        if (!getCode().equals(that.getCode())) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getCode().hashCode();
    }

    @Override
    public String toString() {
        return "TogetherType{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
    }

}
