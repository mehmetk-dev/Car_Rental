package model;

import model.enums.Category;

import java.math.BigDecimal;

public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private Category category;
    private BigDecimal price;
    private BigDecimal rentalRate;

    public Vehicle() {
    }

    public Vehicle(int id, String brand, String model, Category category, BigDecimal price, BigDecimal rentalRate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.rentalRate = rentalRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", rentalRate=" + rentalRate +
                '}';
    }
}
