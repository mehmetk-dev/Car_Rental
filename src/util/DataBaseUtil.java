package util;

import exception.CarRentalException;
import exception.ExceptionMessagesContsants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {
    public DataBaseUtil() {
    }

    public static final String URL = "jdbc:postgresql://localhost:5432/Car_Rental";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "mehmet619";

    static{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            try {
                throw new CarRentalException(ExceptionMessagesContsants.DATABASE_NOT_FOUND);
            } catch (CarRentalException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            try {
                throw new CarRentalException(ExceptionMessagesContsants.DATABASE_NOT_FOUND);
            } catch (CarRentalException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
