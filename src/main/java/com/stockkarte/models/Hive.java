package com.stockkarte.models;

import javax.persistence.*;

@Entity
@Table(name="hives")
public class Hive {
    private long id;
    private String name;
    private String system;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "system", nullable = false)
    public String getSystem() {
        return system;
    }
    public void setSystem(String system) {
        this.system = system;
    }
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    }
