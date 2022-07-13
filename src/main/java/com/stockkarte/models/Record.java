package com.stockkarte.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="RECORD")
public class Record {

    private long id;
    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="hive_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Hive hive;
     */

    private String name;
    private float temperature;
    private String weather;
    private String task;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() { return id; }

    public void setId(long id) { this.id = id;}

    @Column(name="name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="temperature")
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Column(name="weather")
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    // maybe the length property has to be set, the default is 255 and may be to short
    @Column(name="task")
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
