package dao;

import com.sun.source.tree.TryTree;
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

    public List<Vehicle> listPaged(int page, int pageSize) {
        List<Vehicle> vehicleList = new ArrayList<>();



        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.VEHICLE_LIST_PAGE)) {

            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);  // OFFSET = (page-1) * pageSize

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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

    public int getTotalVehicleCount() {
        String sql = "SELECT COUNT(*) FROM vehicles";
        try (Connection connection = DataBaseUtil.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteById(int vehicleId) {

        try(Connection connection = DataBaseUtil.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptsConstans.VEHICLE_DELETE_BY_ID)){

            pr.setInt(1,vehicleId);
            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Vehicle> filterByCategory(Category category) {

        List<Vehicle> filteredList = new ArrayList<>();

        try(Connection connection = DataBaseUtil.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptsConstans.VEHICLE_FILTER_BY_CATEGORY);) {

            pr.setString(1,category.name());

            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setId(rs.getInt("id"));
                vehicle.setCategory(Category.valueOf(rs.getString("category")));
                vehicle.setPrice(rs.getBigDecimal("price"));
                vehicle.setRentalRate(rs.getBigDecimal("rental_rate"));
                vehicle.setAvaible(rs.getBoolean("is_available"));
                filteredList.add(vehicle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  filteredList;
    }

    public Vehicle getById(int vehicleId) {

        Vehicle vehicle = null;

        try(Connection connection = DataBaseUtil.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptsConstans.VEHICLE_FIND_BY_ID)){

            pr.setInt(1,vehicleId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                vehicle = new Vehicle();
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setId(rs.getInt("id"));
                vehicle.setCategory(Category.valueOf(rs.getString("category")));
                vehicle.setPrice(rs.getBigDecimal("price"));
                vehicle.setRentalRate(rs.getBigDecimal("rental_rate"));
                vehicle.setAvaible(rs.getBoolean("is_available"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  vehicle;
    }
}
