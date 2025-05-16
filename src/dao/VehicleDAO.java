package dao;

import dao.constants.SqlScriptsConstans;
import model.Vehicle;
import model.enums.Category;
import util.DataBaseUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    public void save(String brand, String model, Category category, BigDecimal price, BigDecimal rentalRate) {

        try(Connection connection = DataBaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.VEHICLE_SAVE)){

            ps.setString(1,brand);
            ps.setString(2,model);
            ps.setString(3, category.name());
            ps.setBigDecimal(4,price);
            ps.setBigDecimal(5,rentalRate);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> listAll() {

        List<Vehicle> vehicleList = new ArrayList<>();

        try(Connection connection = DataBaseUtil.getConnection();
            Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(SqlScriptsConstans.VEHICLE_LIST_ALL);

            while (rs.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setId(rs.getInt("id"));
                vehicle.setCategory(Category.valueOf(rs.getString("category")));
                vehicle.setPrice(rs.getBigDecimal("price"));
                vehicle.setRentalRate(rs.getBigDecimal("rental_rate"));
                vehicle.setAvaible(rs.getBoolean("is_available"));

                vehicleList.add(vehicle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleList;
    }
}
