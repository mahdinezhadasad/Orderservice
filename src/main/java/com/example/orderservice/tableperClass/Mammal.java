package com.example.orderservice.tableperClass;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Mammal {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    private Integer boyTemp;
    
    private String species;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getBoyTemp() {
        return boyTemp;
    }
    
    public void setBoyTemp(Integer boyTemp) {
        this.boyTemp = boyTemp;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
}