package service;

import dao.RentalDAO;
import model.enums.RentalType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RentalService {
    private RentalDAO rentalDAO;

    public RentalService() {
        this.rentalDAO = new RentalDAO();
    }

    public void save(int userId, int vehicleId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal totalPrice, RentalType monthly) {

        rentalDAO.save(userId,vehicleId,startDate,endDate,totalPrice,monthly);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Araç kiralandı, geri dönüş zamanı " + endDate.format(formatter));
    }
}
