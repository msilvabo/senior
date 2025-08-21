package com.senior.aniversary.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="guest")
public class Guest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String type;

    @Column(name="dateregister", nullable = true)
    private LocalDate dateRegister = LocalDate.now();

    @Column(name="dateinncome")
    private LocalDate dateInncome;

    private Integer quantity;

    @Column(length = 100)
    private String food;

    @Column(length = 100)
    private String drink;

    public Guest() {
    }

    public Guest(Long id, String name, String type, LocalDate dateRegister, Integer quantity, String food,
            String drink) {
        Id = id;
        this.name = name;
        this.type = type;
        this.dateRegister = dateRegister;
        this.quantity = quantity;
        this.food = food;
        this.drink = drink;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public LocalDate getDateInncome() {
        return dateInncome;
    }

    public void setDateInncome(LocalDate dateInncome) {
        this.dateInncome = dateInncome;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }  
    
    
}