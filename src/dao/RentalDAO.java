package dao;

import dao.constants.SqlScriptsConstans;
import model.enums.RentalType;
import util.DataBaseUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RentalDAO {
    public void save(int userId, int vehicleId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal totalPrice, RentalType rentalType)  {

        try(Connection connection = DataBaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.RENTAL_SAVE)){
            ps.setInt(1,userId);
            ps.setInt(2,vehicleId);
            ps.setBigDecimal(3,totalPrice);
            ps.setTimestamp(4, Timestamp.valueOf(startDate));
            ps.setTimestamp(5,Timestamp.valueOf(endDate));
            ps.setString(6,rentalType.name());

            ps.executeUpdate();

            PreparedStatement psUpdate = connection.prepareStatement(SqlScriptsConstans.VEHICLE_UPDATE_AVAILABLE);
            psUpdate.setInt(1,vehicleId);

            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
