import exception.CarRentalException;
import exception.ExceptionMessagesContsants;
import model.User;
import model.enums.UserType;
import service.AdminService;
import service.UserService;
import service.VehicleService;
import util.PasswordUtil;

import java.util.Scanner;

import static model.enums.UserType.ADMIN;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static AdminService adminService = new AdminService();
    private static VehicleService vehicleService = new VehicleService();
    private static User FOUNDED_USER;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

            while (true) {
                getMainMenu();
                String choise = scanner.nextLine();
                try {
                    switch (choise) {
                        case "1":
                            login();
                            break;
                        case "2":
                            registerUser();
                            break;
                        case "0":
                            return;
                        default:
                            System.out.println("Geçersiz işlem.");
                    }
            }catch (CarRentalException e) {
                    System.out.println(ANSI_RED + e.getMessage() +ANSI_RESET);
                }
        }
    }

    public static void login() throws CarRentalException {

        String email = getValidEmail();
        String password = getValidPassword();

        FOUNDED_USER = userService.login(email,password);

        if (FOUNDED_USER.getUserType() == ADMIN){
            getAdminMenu();
        }
    }

    public static void getAdminMenu(){
        while (true){

        System.out.println("=== Admin İşlemleri ===");
        scanner.nextLine();
        }

    }
    public static void getUserMenu(){
        System.out.println("=== Müşteri İşlemleri ===");
    }

    private static void registerUser() throws CarRentalException {

        String email = getValidEmail();
        String password = getValidPassword();
        int age = getValidAge();
        UserType userType = getValidUserType();

        userService.registerUser(email, PasswordUtil.hash(password),age, userType);

    }

    private static void getMainMenu() {
        System.out.println("=== Araç Kiralama Uygulaması() ===");
        System.out.println("Admin girişi email: mehmetkerem şifre: mehmetkerem");
        System.out.println("1 - Giriş Yap");
        System.out.println("2 - Müşteri Kayıt");
        System.out.println("0 - Çıkış");
        System.out.print("İşlem seçiniz: ");
    }

    public static int getValidAge() {
        while (true) {
            System.out.print("Yaşınızı giriniz: ");
            String input = scanner.nextLine();

            try {
                int age = Integer.parseInt(input);

                if (age < 0) {
                    System.out.println(ANSI_RED +"Yaş negatif olamaz, tekrar deneyin."+ ANSI_RESET);
                } else if (age < 18 || age > 66) {
                    System.out.println(ANSI_RED +"Yaşınız 18'den küçük olamaz veya 66'dan büyük olamaz."+ ANSI_RESET);
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + ExceptionMessagesContsants.NUMBER_FORMAT_EXCEPTION+ ANSI_RESET);
            }
        }
    }

    public static String getValidEmail() {
        while (true) {
            System.out.print("E-posta: ");
            String email = scanner.nextLine();

            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

            if (email.matches(regex) || email.equalsIgnoreCase("mehmetkerem")) {
                return email;
            } else {
                System.out.println(ANSI_RED +"Geçersiz e-posta formatı. Tekrar deneyin." + ANSI_RESET);
            }
        }
    }

    public static String getValidPassword() {
        while (true) {
            System.out.print("Şifre giriniz: ");
            String password = scanner.nextLine();

            if (password.length() >= 6) {
                return password;
            } else {
                System.out.println(ANSI_RED +"Şifre en az 6 karakter olmalı." +ANSI_RESET);
            }
        }
    }

    public static UserType getValidUserType(){

        System.out.print("1 - Bireysel Müşteri\n2 - Kurumsal Müşteri\nLütfen Müşteri Türünü Giriniz: ");
        while (true){

        String choice = scanner.nextLine();
        UserType userType = null;
        if (choice.equalsIgnoreCase("1")){
            return UserType.INDIVIDUAL;
        } else if (choice.equalsIgnoreCase("2")) {
            return UserType.CORPORATE;
        }else{
            System.out.println(ANSI_RED +"Yanlış bir değer girdiniz tekrar deneyiniz" + ANSI_RESET);
            System.out.print("Lütfen Müşteri Türünü Giriniz:");

        }
        }
    }
}
