package service;

import dao.VehicleDAO;
import model.Vehicle;
import model.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public class VehicleService {

    private VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void save(String brand, String model, Category category, BigDecimal price, BigDecimal rentalRate) {

        vehicleDAO.save(brand,model,category,price,rentalRate);
        System.out.println("\u001B[32mİşlem başarılı!\u001B[0m");
    }

    public List<Vehicle> listAll() {

        return vehicleDAO.listAll();
    }

    public List<Vehicle> listVehiclesPaged(int page, int pageSize) {
        return vehicleDAO.listPaged(page, pageSize);
    }

    public int getTotalVehicleCount() {
        return vehicleDAO.getTotalVehicleCount();
    }

    public void deleteById(int vehicleId) {

        vehicleDAO.deleteById(vehicleId);
        System.out.println("Araç başarıyla silindi!");
    }
}
