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
    private Boolean isAvaible;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, Category category, BigDecimal price, BigDecimal rentalRate) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.rentalRate = rentalRate;
        this.isAvaible = true;
    }

    public Boolean getAvaible() {
        return isAvaible;
    }

    public void setAvaible(Boolean avaible) {
        isAvaible = avaible;
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
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RED = "\u001B[31m";

        String durum = isAvaible ? "Uygun" : "Kiralanmış";
        String durumRenkli = (durum.equalsIgnoreCase("Uygun") || durum.equalsIgnoreCase("available"))
                ? ANSI_GREEN + durum + ANSI_RESET
                : ANSI_RED + durum + ANSI_RESET;

        return String.format("ID:%-4d Marka:%-8s Model:%-8s Kategori:%-4s Fiyat:%-4s ₺ Kiralama:%-4s ₺ Durum:%s",
                id, brand, model, category, price, rentalRate, durumRenkli);
    }
}
