package com.stockkarte.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stockkarte.models.User;
@Entity
@Table(name="HIVE")
public class Hive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String system;

    @JsonIgnoreProperties({"hives"})
    @ManyToOne
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
