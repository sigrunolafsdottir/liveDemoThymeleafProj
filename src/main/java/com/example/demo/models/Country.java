package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    //cascade all så att vi autosparar en capital när vi sparar ett Country till db, se exempel i CommandLineRunnern i DemoApplication-klassen
    @JoinColumn(name = "capitalId", referencedColumnName = "id")
    private Capital capital;                                             //bara för att ha en 1-1-relation att dema

    @OneToMany(mappedBy = "country")
    @JsonBackReference
    private List<Child> children;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, Capital cap) {
        this.name = name;
        capital = cap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
}
