package ua.mai.fam.model;

import javax.persistence.*;

@Entity
@Table(name = "PARENT_CHILD")
public class ParentChild {

    @EmbeddedId
    private ParentChildId id = new ParentChildId();

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
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
