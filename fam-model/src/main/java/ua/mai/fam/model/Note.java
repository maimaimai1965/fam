package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ARTIFACT_ID", nullable = false)
    private Artifact artifact;

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

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public Artifact getArtifact() {
        return artifact;
    }
    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
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

        if (id != note.id) return false;
        if (!person.equals(note.person)) return false;
        if (!artifact.equals(note.artifact)) return false;
        return description.equals(note.description);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + person.hashCode();
        result = 31 * result + artifact.hashCode();
        return result;
    }

}
