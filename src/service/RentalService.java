package service;

import dao.RentalDAO;
import model.Rental;
import model.enums.RentalType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class RentalService {
    private RentalDAO rentalDAO;

    public RentalService() {
        this.rentalDAO = new RentalDAO();
    }

    public void save(int userId, int vehicleId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal totalPrice, RentalType monthly) {

        rentalDAO.save(userId,vehicleId,startDate,endDate,totalPrice,monthly);

    }

    public List<Rental> getPastRental(int userId) {

        return rentalDAO.getPastRental(userId);
    }

    public void returnVehicle(int returnCarId) {

        rentalDAO.returnVehicle(returnCarId);
    }

    public List<Rental> getAllRentals() {

        return rentalDAO.getAllRentals();
    }
}
