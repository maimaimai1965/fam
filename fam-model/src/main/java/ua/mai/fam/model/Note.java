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
@Table(name = "NOTE")
public class Note {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    //--for JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_note")
    @SequenceGenerator(name="seq_note", sequenceName="SEQ_NOTE", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @NotNull
    @Column(name = "description", length = 500)
    private String description;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
            Objects.equals(description, note.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
