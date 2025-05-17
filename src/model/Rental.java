package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rental {
    private int id;
    private int userId;
    private int vehicleId;
    private BigDecimal totalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isReturned;

    public Rental() {
    }

    public Rental(int userId, int vehicleId, BigDecimal totalPrice, LocalDateTime startDate, LocalDateTime endDate) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.totalPrice = totalPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReturned = false;
    }

    public Rental(int id, int userId, int vehicleId, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReturned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
