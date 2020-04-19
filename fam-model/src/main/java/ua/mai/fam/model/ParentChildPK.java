package ua.mai.fam.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ParentChildPK implements Serializable {

    @Id
    @Column(name = "PARENT_ID", nullable = false)
    private long parentId;

    @Id
    @Column(name = "CHILD_ID", nullable = false)
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
        ParentChildPK that = (ParentChildPK) o;
        return parentId == that.parentId && childId == that.childId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, childId);
    }

}
