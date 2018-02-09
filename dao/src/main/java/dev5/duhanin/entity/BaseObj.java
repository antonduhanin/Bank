package dev5.duhanin.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
