package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "ARTIFACT")
public class Artifact {

    //--for Data JDBC
    @org.springframework.data.annotation.Id
    //--for Data JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_artifact")
    @SequenceGenerator(name="seq_artifact", sequenceName="SEQ_ARTIFACT", allocationSize = 20)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @Basic
    @Column(name = "description", length = 500)
    private String description;

    @Basic
    @Column(name = "link", length = 200)
    private String link;


    public long getId() {
        return id;
    }
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artifact artifact = (Artifact) o;
        return id == artifact.id &&
            Objects.equals(name, artifact.name) &&
            Objects.equals(description, artifact.description) &&
            Objects.equals(link, artifact.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, link);
    }
}
