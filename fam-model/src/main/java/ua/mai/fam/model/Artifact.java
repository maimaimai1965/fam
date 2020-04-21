package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "ARTIFACT_TYPE_CODE", nullable = false)
    private ArtifactType artifactType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Person owner;

    @Basic
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Basic
    @Column(name = "LINK", length = 200)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOX_ID", nullable = true)
    private Box box;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArtifactType getArtifactType() {
        return artifactType;
    }
    public void setArtifactType(ArtifactType artifactType) {
        this.artifactType = artifactType;
    }

    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
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

    public Box getBox() {
        return box;
    }
    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artifact artifact = (Artifact) o;

        if (id != artifact.id) return false;
        if (name != null ? !name.equals(artifact.name) : artifact.name != null) return false;
        if (!artifactType.equals(artifact.artifactType)) return false;
        if (description != null ? !description.equals(artifact.description) : artifact.description != null)
            return false;
        return link != null ? link.equals(artifact.link) : artifact.link == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + artifactType.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

}
