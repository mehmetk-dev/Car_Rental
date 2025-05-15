package dao;

import dao.constants.SqlScriptsConstans;
import exception.CarRentalException;
import exception.ExceptionMessagesContsants;
import model.User;
import model.enums.UserType;
import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void save(String email, String password, int age, UserType userType) throws CarRentalException {

        try(Connection connection = DataBaseUtil.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptsConstans.USER_SAVE)){

            pr.setInt(1,age);
            pr.setString(2,email);
            pr.setString(3,password);
            pr.setString(4,userType.name());

            pr.executeUpdate();

        } catch (SQLException e) {
            throw new CarRentalException(ExceptionMessagesContsants.DATABASE_NOT_FOUND);
        }
    }

    public boolean isExistByEmail(String email){

        try(Connection connection = DataBaseUtil.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptsConstans.USER_FIND_BY_EMAIL)) {

            pr.setString(1,email);
            ResultSet rs = pr.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByEmail(String email){

        User user = null;

        try(Connection connection = DataBaseUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptsConstans.USER_FIND_BY_EMAIL)) {

            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                user.setPassword(rs.getString("password"));
                user.setUserType(UserType.valueOf(rs.getString("user_type")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void login(String email, String hashedPassword) {


    }
}
