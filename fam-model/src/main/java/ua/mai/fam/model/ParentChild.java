package ua.mai.fam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)   //Не выводить null поля в JSON
@JsonIgnoreProperties(ignoreUnknown = false)
//--For JPA
@Entity
@Table(name = "PARENT_CHILD")
@IdClass(ParentChildPK.class)
public class ParentChild {

    @Id
    @Column(name = "parent_id", nullable = false)
    private long parentId;

    @Id
    @Column(name = "child_id", nullable = false)
    private long childId;


    public long getParentId() {
        return parentId;
    }
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getChildId() {
        return childId;
    }
    public void setChildId(long childId) {
        this.childId = childId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentChild that = (ParentChild) o;
        return parentId == that.parentId &&
            childId == that.childId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, childId);
    }
}
