package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "BOX")
public class Box {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    //--for JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_box")
    @SequenceGenerator(name="seq_box", sequenceName="SEQ_BOX", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "description", length = 100)
    private String description;

    @Basic
    @Column(name = "box_char", length = 1)
    private String boxChar;

    @Basic
    @Column(name = "box_bin", length = -1)
    private String boxBin;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getBoxChar() {
        return boxChar;
    }
    public void setBoxChar(String boxChar) {
        this.boxChar = boxChar;
    }

    public String getBoxBin() {
        return boxBin;
    }
    public void setBoxBin(String boxBin) {
        this.boxBin = boxBin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return id == box.id &&
            Objects.equals(description, box.description) &&
            Objects.equals(boxChar, box.boxChar) &&
            Objects.equals(boxBin, box.boxBin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, boxChar, boxBin);
    }
}
