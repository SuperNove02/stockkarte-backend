package com.stockkarte.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="HIVE")
public class Hive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String system;

    //@OneToMany(targetEntity = Record.class)
    @OneToMany(mappedBy ="hive", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Record> records = new ArrayList<Record>();

    public void addRecord(Record record) {
        record.setHive(this);
        records.add(record);
    }

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
