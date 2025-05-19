package dao;

import dao.constants.SqlScriptsConstans;
import model.Rental;
import model.User;
import model.Vehicle;
import model.enums.Category;
import model.enums.RentalType;
import util.DataBaseUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {
    public void save(int userId, int vehicleId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal totalPrice, RentalType rentalType) {

        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.RENTAL_SAVE)) {
            ps.setInt(1, userId);
            ps.setInt(2, vehicleId);
            ps.setBigDecimal(3, totalPrice);
            ps.setTimestamp(4, Timestamp.valueOf(startDate));
            ps.setTimestamp(5, Timestamp.valueOf(endDate));
            ps.setString(6, rentalType.name());

            ps.executeUpdate();

            PreparedStatement psUpdate = connection.prepareStatement(SqlScriptsConstans.VEHICLE_UPDATE_AVAILABLE_FALSE);
            psUpdate.setInt(1, vehicleId);

            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rental> getPastRental(int userId) {

        List<Rental> rentals = new ArrayList<>();

        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.RENTAL_ALL_LIST_BY_USER_ID)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rental rental = new Rental();
                rental.setId(rs.getInt("rental_id"));
                rental.setVehicle(new Vehicle(rs.getString("model"),
                        rs.getString("brand"),
                        Category.valueOf(rs.getString("vehicle_category"))));
                rental.setTotalPrice(rs.getBigDecimal("total_price"));
                rental.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                rental.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
                rental.setReturned(rs.getBoolean("is_returned"));
                rentals.add(rental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    public void returnVehicle(int returnCarId) {

        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.RENTAL_RETURN_CAR)) {
            ps.setInt(1, returnCarId);

            ps.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement(SqlScriptsConstans.VEHICLE_UPDATE_AVAILABLE_TRUE);
            ps2.setInt(1, returnCarId);

            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Rental> getAllRentals() {

        List<Rental> rentals = new ArrayList<>();

        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.RENTAL_ALL_LIST)) {


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rental rental = new Rental();
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                rental.setUser(user);
                rental.setId(rs.getInt("rental_id"));
                rental.setVehicle(new Vehicle(rs.getString("model"),
                        rs.getString("brand"),
                        Category.valueOf(rs.getString("vehicle_category"))));
                rental.setTotalPrice(rs.getBigDecimal("total_price"));
                rental.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                rental.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
                rental.setReturned(rs.getBoolean("is_returned"));
                rentals.add(rental);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentals;
    }
}