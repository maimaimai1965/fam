package ua.mai.fam.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.mai.fam.model.TogetherType;
import ua.mai.fam.util.HasId;
import ua.mai.fam.util.ToEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
public class TogetherTypeDto implements HasId<String>, ToEntity<TogetherType>, Serializable {

    //for Data JDBC
    @org.springframework.data.annotation.Id
    private String code;

    @NotNull
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


    public TogetherTypeDto() {}

    public TogetherTypeDto(String code, String name) {
        this.code = code;
        this.name = name;
    }


    @Override
    public String getTableName() {
        return "TOGETHER_TYPE";
    }
    @Override
    public String getId() {
        return code;
    }

    @Override
    public TogetherType toEntity() {
        return new TogetherType(code, name);
    }

    public static List<TogetherType> toEntities(Collection<PersonDto> dtos)  {
        return (List<TogetherType>) ToEntity.toEntities(dtos);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TogetherTypeDto that = (TogetherTypeDto) o;

        if (!code.equals(that.code)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TogetherType{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
    }

}
