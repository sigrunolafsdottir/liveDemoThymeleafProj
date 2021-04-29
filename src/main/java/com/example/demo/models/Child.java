package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Child implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId", referencedColumnName = "id")
    @JsonManagedReference
    private Country country;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "wishes", joinColumns = {@JoinColumn(referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private List<Present> wishes;

    //private List<Present> gifts;


    public Child() {
    }

    public Child(String name, Country country, List<Present> wishes) {
        this.name = name;
        this.country = country;
        this.wishes = wishes;
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

    public List<Present> getWishes() {
        return wishes;
    }

    public void setWishes(List<Present> wishes) {
        this.wishes = wishes;
    }

    /*
        public List<Present> getGifts() {
            return gifts;
        }

        public void setGifts(List<Present> gifts) {
            this.gifts = gifts;
        }
    */
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
