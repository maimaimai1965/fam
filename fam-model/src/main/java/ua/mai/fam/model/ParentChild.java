package ua.mai.fam.model;

import ua.mai.fam.model.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PARENT_CHILD")
public class ParentChild {

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "PARENT_ID")
        protected Long parentId;

        @Column(name = "CHILD_ID")
        protected Long childId;

        public Id() {
        }

        public Id(Long parentId, Long childId) {
            this.parentId = parentId;
            this.childId = childId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;
            if (!parentId.equals(id.parentId)) return false;
            return childId.equals(id.childId);
        }

        @Override
        public int hashCode() {
            int result = parentId.hashCode();
            result = 31 * result + childId.hashCode();
            return result;
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", insertable = false, updatable = false)
    protected Person parent;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID", insertable = false, updatable = false)
    protected Person child;


    public ParentChild() {
    }

    public ParentChild(Person parent, Person child) {
        this.parent = parent;
        this.child = child;

        // Set identifier values
        this.id.parentId = parent.getId();
        this.id.childId = child.getId();

        // Guarantee referential integrity if made bidirectional
        parent.getChildren().add(this);
        child.getParents().add(this);
    }

}
