package service;

import dao.UserDAO;
import exception.CarRentalException;
import exception.ExceptionMessagesContsants;
import model.User;
import model.enums.UserType;
import util.PasswordUtil;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(String email, String password, int age, UserType userType) throws CarRentalException {

        boolean isExist = userDAO.isExistByEmail(email);

        if (isExist){
            throw new CarRentalException(ExceptionMessagesContsants.USER_EMAIL_ALLREADY_EXIST);
        }
        userDAO.save(email,password,age,userType);
        System.out.println("\u001B[32mİşlem başarılı!\u001B[0m");
    }

    public User login(String email, String password) throws CarRentalException {

        User foundedUser = userDAO.findByEmail(email);

        if (foundedUser != null){
            String hashedPassword = PasswordUtil.hash(password);
            if (!hashedPassword.equalsIgnoreCase(foundedUser.getPassword())){
                throw new CarRentalException(ExceptionMessagesContsants.USER_PASSWORD_OR_EMAIL_NOT_MATCH);
            }
        }else{
            throw new CarRentalException(ExceptionMessagesContsants.USER_PASSWORD_OR_EMAIL_NOT_MATCH);
        }
        System.out.println("Giriş Başarılı " + foundedUser.getEmail());
        return foundedUser;
    }
}
