package ua.mai.fam.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ParentChildId implements Serializable {

    @Column(name = "PARENT_ID")
    protected Long parentId;

    @Column(name = "CHILD_ID")
    protected Long childId;

    public ParentChildId() {
    }

    public ParentChildId(Long parentId, Long childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentChildId)) return false;

        ParentChildId that = (ParentChildId) o;

        if (!parentId.equals(that.parentId)) return false;
        return childId.equals(that.childId);
    }

    @Override
    public int hashCode() {
        int result = parentId.hashCode();
        result = 31 * result + childId.hashCode();
        return result;
    }
}
