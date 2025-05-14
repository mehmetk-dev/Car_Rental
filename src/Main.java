import service.AdminService;
import service.AuthService;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static AdminService adminService = new AdminService();

    public static void main(String[] args) {

        while (true){

        getMainMenu();
        String choise = scanner.nextLine();

        try{
            switch (choise){
                case "1":
                    getAdminMenu();
                    break;
                case "2":
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz işlem.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }}
    }

    private static void getAdminMenu() {
        while(true) {

            System.out.println("=== ADMIN GİRİŞ PANELİ ===");
            System.out.println("1 - Giriş Yap");
            System.out.println("2 - Kayıt Ol");
            System.out.println("0 - Geri Dön");

            String choise = scanner.nextLine();

            switch (choise){
                case "1":
                    loginAdmin();
                    break;
                case "2":
                    registerAdmin();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz işlem.");
            }
        }
    }

    private static void loginAdmin() {
    }

    private static void registerAdmin() {

        System.out.print("E-Posta adresinizi giriniz: ");
        String email = scanner.nextLine();

        System.out.print("Şifrenizi Giriniz: ");
        String password = scanner.nextLine();

        System.out.print("Yaşınızı Giriniz: ");
        String age = scanner.nextLine();

        AdminService.login(email,password,age,true);
    }

    private static void getMainMenu() {
        System.out.println("=== Araç Kiralama Uygulaması ===");
        System.out.println("Giriş Türünü Seçiniz");
        System.out.println("1 - Admin Girişi");
        System.out.println("2 - Müşteri Girişi");
        System.out.println("0 - Çıkış");
        System.out.print("İşlem seçiniz: ");
    }
}