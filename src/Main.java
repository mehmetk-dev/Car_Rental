import exception.CarRentalException;
import exception.ExceptionMessagesContsants;
import model.Rental;
import model.User;
import model.Vehicle;
import model.enums.Category;
import model.enums.RentalType;
import model.enums.UserType;
import service.AdminService;
import service.RentalService;
import service.UserService;
import service.VehicleService;
import util.PasswordUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static model.enums.UserType.*;

public class Main {
    // Scanner ve servis sınıfları tanımlanıyor
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final AdminService adminService = new AdminService();
    private static final VehicleService vehicleService = new VehicleService();
    private static final RentalService rentalService = new RentalService();
    private static User FOUNDED_USER;

    // Renkli konsol çıktısı için ANSI kodları
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        while (true) {
            System.out.println(ANSI_GREEN +"Admin E-posta: mehmetkerem - Şifre: mehmetkerem\n" +
                    "Kurumsal E-posta: mehmetkerem8@gmail.com - Şifre: mehmetkerem\n" +
                    "Bireysel E-posta: mehmetkerem9@gmail.com - Şifre: mehmetkerem"+ ANSI_RESET);
            getMainMenu();
            System.out.print("İşlem Seçiniz:");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        login(); // Kullanıcı girişi
                        break;
                    case "2":
                        registerUser(); // Yeni kullanıcı kaydı
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println(ANSI_RED + "Geçersiz işlem, tekrar deneyiniz." + ANSI_RESET);
                }
            } catch (CarRentalException  | InterruptedException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            }
        }
    }
    // Ana menü gösterimi
    private static void getMainMenu() {
        System.out.println("=== Araç Kiralama Uygulaması ===");
        System.out.println("1 - Giriş Yap");
        System.out.println("2 - Müşteri Kayıt");
        System.out.println("0 - Çıkış");
    }

    // Kullanıcı girişi
    public static void login() throws CarRentalException, InterruptedException {
        String email = getValidEmail();
        String password = getValidPassword();
        FOUNDED_USER = userService.login(email, password);

        if (FOUNDED_USER.getUserType() == ADMIN) {
            getAdminMenu();
        } else if (FOUNDED_USER.getUserType() == CORPORATE || FOUNDED_USER.getUserType() == INDIVIDUAL) {
            getUserMenu();
        } else {
            throw new CarRentalException(ExceptionMessagesContsants.USER_GIVE_AN_ERROR);
        }
    }

    // Yeni kullanıcı kaydı
    private static void registerUser() throws CarRentalException {
        String email = getValidEmail();
        String password = getValidPassword();
        int age = getValidAge();
        UserType userType = getValidUserType();

        userService.registerUser(email, PasswordUtil.hash(password), age, userType);
    }

    // Admin menüsü
    public static void getAdminMenu() throws CarRentalException {
        while (true) {
            System.out.println("=== Admin İşlemleri ===");
            System.out.println("1 - Araç Ekle");
            System.out.println("2 - Araç Sil");
            System.out.println("3 - Araç Listele");
            System.out.println("4 - Kiralamaları Listele");
            System.out.println("0 - Çıkış");
            System.out.print("İşlem Seçiniz:");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addVehicle();
                    break;
                case "2":
                    deleteVehicle();
                    break;
                case "3":
                    showVehicleListWithPaging();
                    break;
                case "4":
                    listAllRental();
                    break;
                case "0":
                    return;
                default:
                    System.out.println(ANSI_RED + "Geçersiz işlem, tekrar deneyiniz." + ANSI_RESET);
            }
        }
    }

    // Müşteri menüsü
    public static void getUserMenu() throws CarRentalException, InterruptedException {
        while (true) {
            System.out.println("=== Müşteri İşlemleri ===");
            System.out.println("1 - Araç Listele");
            System.out.println("2 - Kategoriye Göre Araç Listele");
            System.out.println("3 - Araç Kirala");
            System.out.println("4 - Araç Teslim Et");
            System.out.println("5 - Önceki Kiralamalarımı Görüntüle");
            System.out.println("0 - Çıkış");
            System.out.print("İşlem seçiniz: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showVehicleListWithPaging();
                    break;
                case "2":
                    filterVehicle();
                    break;
                case "3":
                    rentACar();
                    break;
                case "4":
                    returnVehicle();
                    break;
                case "5":
                    getPastRentals();
                    break;
                case "0":
                    return;
                default:
                    System.out.println(ANSI_RED + "Geçersiz işlem." + ANSI_RESET);
            }
        }
    }

    // Araç kiralama
    private static void rentACar() throws CarRentalException {

        System.out.println("1 - Araba");
        System.out.println("2 - Motosiklet");
        System.out.println("3 - Helikopter");
        System.out.println("Ne Tür Araç Kiralamak İstiyorsunuz");
        String categoryStr = getInvalidCategory();
        Category category = Category.valueOf(categoryStr);

        List<Vehicle> vehicleList = vehicleService.filterVehicleByCategory(category);
        if (vehicleList.isEmpty()) {
            System.out.println("Bu kategoriye ait araç bulunamadı.");
        } else {
            vehicleList.forEach(vehicle -> {
                if (vehicle.getAvaible()){
                    System.out.println(vehicle);
                }
            });
        }

        System.out.println("Kiralamak İstediğiniz Aracın ID'sini Giriniz: ");
        String carId = scanner.nextLine();

        Vehicle vehicle = vehicleService.getById(Integer.parseInt(carId));
        //ID ve araç müsaitlik durummu kontrolü
        if (vehicle == null || !vehicle.getAvaible()){
            throw new CarRentalException(ExceptionMessagesContsants.CAR_NOT_FOUND);
        }

        System.out.println(vehicle);

        // Değerli araçlar için yaş kontrolü ve depozito
        if (vehicle.getPrice().compareTo(BigDecimal.valueOf(2_000_000)) > 0){
            if (FOUNDED_USER.getAge() <= 30){
                throw new CarRentalException(ExceptionMessagesContsants.USER_AGE_VALIDATION_FAILED);
            }
            BigDecimal deposit = vehicle.getPrice().multiply(BigDecimal.valueOf(0.1));
            System.out.println("Depozito tutarınız " + deposit + " TL");
        }

        System.out.println("Aracı kiralamak istiyorsanız 'E' tuşuna basınız.");
        String choice = scanner.nextLine();

        if (!choice.equalsIgnoreCase("e") ){
            return;
        }

        if (FOUNDED_USER.getUserType() == CORPORATE){
            handleCorporateRental(vehicle); // Kurumsal kiralama
        }else{
            handleIndividualRental(vehicle); // Bireysel kiralama
        }
    }

    // Bireysel kullanıcı için kiralama işlemi
    private static void handleIndividualRental(Vehicle vehicle) {

        System.out.println("Kiralama türünü seçiniz:");
        System.out.println("1 - Saatlik");
        System.out.println("2 - Günlük");
        System.out.println("3 - Haftalık");
        System.out.println("4 - Aylık");
        System.out.print("Seçiminiz: ");
        String choice =  scanner.nextLine();

        RentalType rentalType;

        switch (choice) {
            case "1" -> rentalType = RentalType.HOURLY;
            case "2" -> rentalType = RentalType.DAILY;
            case "3" -> rentalType = RentalType.WEEKLY;
            case "4" -> rentalType = RentalType.MONTHLY;
            default -> {
                System.out.println("Geçersiz seçim yaptınız.");
                return;
            }}

        int duration = readPositiveInt("Kaç " + rentalType.getDisplayName() + " kiralamak istiyorsunuz: ");

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = switch (rentalType) {
            case HOURLY -> startDate.plusHours(duration);
            case DAILY -> startDate.plusDays(duration);
            case WEEKLY -> startDate.plusWeeks(duration);
            case MONTHLY -> startDate.plusMonths(duration);
        };

        // Tarih bilgisini gün ay yıl ve saat şeklinde yazdırmak için
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        // Ücret hesaplama
        int totalHours = duration * rentalType.getHourMultiplier();
        BigDecimal hourlyRate = vehicle.getRentalRate();
        BigDecimal totalPrice = hourlyRate.multiply(BigDecimal.valueOf(totalHours));

        System.out.println(duration + " " + rentalType.getDisplayName() + " Kiralama Ücreti: " + totalPrice + "₺");
        System.out.println("Başlangıç Tarihi = " + formattedStartDate +";\nBitiş Tarihi = " +formattedEndDate + ";");

        rentalService.save(FOUNDED_USER.getId(),vehicle.getId(),startDate,endDate,totalPrice,rentalType);
        vehicle.setAvaible(false);
    }

    // Kurumsal kullanıcılar için kiralama işlemi
    private static void handleCorporateRental(Vehicle vehicle) throws CarRentalException {

        System.out.println( ANSI_YELLOW +"Dikkat: Kurumsal kullanıcılar en az 1 aylık kiralama yapabilirler." + ANSI_RESET);
        System.out.print("Kiralamak İstediğiniz Ay Sayısını Giriniz: ");
        int monthCount = scanner.nextInt();
        scanner.nextLine();

        if (monthCount < 1 || monthCount > 24){ //1 aydan kısa veya 24 aydan uzun kiralayamaz
            throw new CarRentalException(ExceptionMessagesContsants.INVALID_RENTAL_DURATION);
        }
        //Ücret hesaplama
        BigDecimal hourlyRate = vehicle.getRentalRate();
        BigDecimal hoursInMonth = BigDecimal.valueOf(RentalType.MONTHLY.getHourMultiplier());
        BigDecimal totalHours = hoursInMonth.multiply(BigDecimal.valueOf(monthCount));
        BigDecimal totalPrice = hourlyRate.multiply(totalHours);

        System.out.println(monthCount +" Ay İçin Toplam Ücret: " + totalPrice + " TL");

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusMonths(monthCount);

        rentalService.save(FOUNDED_USER.getId(),vehicle.getId(),startDate,endDate,totalPrice, RentalType.MONTHLY);
        vehicle.setAvaible(false);
    }

    // Araç Ekleme
    private static void addVehicle() {
        System.out.println("=== Araç Bilgileri ===");
        System.out.print("Marka Giriniz: ");
        String brand = scanner.nextLine();

        System.out.print("Model Giriniz: ");
        String model = scanner.nextLine();

        System.out.println("1 - Araba\n2 - Motosiklet\n3 - Helikopter");
        System.out.print("Aracın Kategorisini Seçiniz: ");
        String category = getInvalidCategory();

        System.out.print("Aracın Değerini Giriniz: ");
        String price = scanner.nextLine();

        System.out.print("Aracın Kiralama İçin Saatlik Ücretini Giriniz: ");
        String rentalRate = scanner.nextLine();

        printVehiclePreview(brand, model, category, new BigDecimal(price), new BigDecimal(rentalRate));

        System.out.print("Aracı eklemek istiyor musunuz?(E/H): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("E")) {
            vehicleService.save(brand, model, Category.valueOf(category), new BigDecimal(price), new BigDecimal(rentalRate));
        } else {
            System.out.println("İşlem iptal edildi.");
        }
    }

    //Bütün kiralamaları getiren metot
    private static void listAllRental() {

        List<Rental> allRental = rentalService.getAllRentals();
        for (Rental rental : allRental) {
            System.out.printf("\nMüşteri ID: %s - E-Posta: %s - Yaş: %s\n",rental.getUser().getId(),
                    rental.getUser().getEmail(),rental.getUser().getAge());
            System.out.printf("İşlem ID: %s - Kategori: %s - Marka: %s - Model: %s\n",rental.getId(),rental.getVehicle().
                    getCategory().name(), rental.getVehicle().getBrand(), rental.getVehicle().getModel());
            System.out.printf("Ücret: %s - Başlangıç Tarihi: %s - Bitiş Tarihi: %s",rental.getTotalPrice(),
                    rental.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                    rental.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            System.out.print("\n---------------------");
        }
        System.out.println();
    }

    //Araç iade metodu
    private static void returnVehicle() {

        List<Rental> pastRentalList = rentalService.getPastRental(FOUNDED_USER.getId());
        pastRentalList.stream()
                .filter(rental -> !rental.isReturned())
                .forEach(rentals -> {
                    String status = ANSI_RED + "Araç İade edilmedi" + ANSI_RESET;
                    printRentalDetails(rentals, status);
                });

        System.out.print("İade etmek istediğiniz aracın işlem ID'sini giriniz: ");
        String returnCarId = scanner.nextLine();

        rentalService.returnVehicle(Integer.parseInt(returnCarId));
    }

    //Kullanıcının bütün kiralamalarını gösteren metod
    private static void getPastRentals() throws InterruptedException {

        List<Rental> pastRentalList = rentalService.getPastRental(FOUNDED_USER.getId());

        pastRentalList.forEach(rentals -> {
            String status = rentals.isReturned() ? ANSI_GREEN + "Araç İade Edildi" + ANSI_RESET : ANSI_RED + "Araç İade edilmedi" + ANSI_RESET;
            printRentalDetails(rentals, status);
        });
    }

    //Araç silme
    private static void deleteVehicle() throws CarRentalException {

            listAllVehicle();
            System.out.print("Silmek İstediğiniz Aracın ID'sini Giriniz: ");
            String id = scanner.nextLine();

            vehicleService.deleteById(Integer.parseInt(id));

            System.out.println("Güncel Liste:");
            listAllVehicle();

    }

    //Araç listeleme
    private static void listAllVehicle() {
        List<Vehicle> vehicleList = vehicleService.listAll();
        System.out.println("===Araç Listesi===");
        vehicleList.forEach(System.out::println);
    }

    //Araç listreleme
    private static void filterVehicle() {
        System.out.println("1 - Araba");
        System.out.println("2 - Motosiklet");
        System.out.println("3 - Helikopter");
        System.out.print("Görüntülemek İstediğiniz Kategoriyi Seçiniz: ");

        String categoryStr = getInvalidCategory();
        Category category = Category.valueOf(categoryStr);

        List<Vehicle> vehicleList = vehicleService.filterVehicleByCategory(category);
        if (vehicleList.isEmpty()) {
            System.out.println("Bu kategoriye ait araç bulunamadı.");
        } else {
            vehicleList.forEach(System.out::println);
        }
    }

    //Kategori kontrol metodu
    private static String getInvalidCategory() {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    return Category.CAR.name();
                case "2":
                    return Category.MOTORCYCLE.name();
                case "3":
                    return Category.HELICOPTER.name();
                default:
                    System.out.println("Yanlış değer tekrar deneyiniz.");
            }
        }
    }

    //Yaş kontrol metodu
    public static int getValidAge() {
        while (true) {
            System.out.print("Yaşınızı giriniz: ");
            String input = scanner.nextLine();
            try {
                int age = Integer.parseInt(input);
                if (age < 0) {
                    System.out.println(ANSI_RED + "Yaş negatif olamaz, tekrar deneyin." + ANSI_RESET);
                } else if (age < 18 || age > 66) {
                    System.out.println(ANSI_RED + "Yaşınız 18'den küçük veya 66'dan büyük olamaz." + ANSI_RESET);
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + ExceptionMessagesContsants.NUMBER_FORMAT_EXCEPTION + ANSI_RESET);
            }
        }
    }

    //Email kontrol metodu
    public static String getValidEmail() {
        while (true) {
            System.out.print("E-posta: ");
            String email = scanner.nextLine();
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (email.matches(regex) || email.equalsIgnoreCase("mehmetkerem")) {
                return email;
            } else {
                System.out.println(ANSI_RED + "Geçersiz e-posta formatı. Tekrar deneyin." + ANSI_RESET);
            }
        }
    }

    //Şifre kontrol metodu
    public static String getValidPassword() {
        while (true) {
            System.out.print("Şifre giriniz: ");
            String password = scanner.nextLine();
            if (password.length() >= 6) {
                return password;
            } else {
                System.out.println(ANSI_RED + "Şifre en az 6 karakter olmalı." + ANSI_RESET);
            }
        }
    }

    //Kullanıcı giriş kontrol metodu
    public static UserType getValidUserType() {
        System.out.print("1 - Bireysel Müşteri\n2 - Kurumsal Müşteri\nLütfen Müşteri Türünü Giriniz: ");
        while (true) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return UserType.INDIVIDUAL;
                case "2":
                    return UserType.CORPORATE;
                default:
                    System.out.println(ANSI_RED + "Yanlış bir değer girdiniz, tekrar deneyiniz." + ANSI_RESET);
                    System.out.print("Lütfen Müşteri Türünü Giriniz: ");
            }
        }
    }

    //Araç listeleme formatı için yazdığım metot
    public static void printVehiclePreview(String brand, String model, String category, BigDecimal price, BigDecimal rentalRate) {
        String yF = "\u001B[33m"; // Sarı
        String yE = "\u001B[0m";  // Reset

        System.out.printf("Marka: %s%s%s - Model: %s%s%s - Kategori: %s%s%s - Değer: %s%.2f₺%s\n",
                yF, brand, yE, yF, model, yE, yF, category, yE, yF, price, yE);

        System.out.printf("Kiralama Ücretleri - Saatlik: %s%.2f₺%s - Günlük: %s%.2f₺%s - Haftalık: %s%.2f₺%s - Aylık: %s%.2f₺%s\n",
                yF, rentalRate, yE,
                yF, rentalRate.multiply(BigDecimal.valueOf(24)), yE,
                yF, rentalRate.multiply(BigDecimal.valueOf(24 * 7)), yE,
                yF, rentalRate.multiply(BigDecimal.valueOf(24 * 30)), yE);
    }

    //Araç listesini sayfalamak için kullanılan metot
    public static void showVehicleListWithPaging() {
        int currentPage = 1;
        int pageSize = 3;
        int totalVehicles = vehicleService.getTotalVehicleCount();
        int totalPages = (int) Math.ceil((double) totalVehicles / pageSize);

        while (true) {
            System.out.println("\nSayfa " + currentPage + "/" + totalPages);
            List<Vehicle> vehicles = vehicleService.listVehiclesPaged(currentPage, pageSize);
            vehicles.forEach(System.out::println);

            System.out.println("\n[N]ext - [P]revious - [Q]uit");
            System.out.print("Seçim: ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "n":
                    if (currentPage < totalPages) currentPage++;
                    break;
                case "p":
                    if (currentPage > 1) currentPage--;
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Geçersiz giriş.");
            }
        }
    }

    //Kullanıcıdan alınan sayısal değer kontrolü
    public static int readPositiveInt(String prompt) {
        int value = -1;
        while (value < 0) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("Lütfen pozitif bir sayı giriniz.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı giriniz.");
            }
        }
        return value;
    }

    private static void printRentalDetails(Rental rentals, String status) {
        System.out.printf("%s\nİşlem ID: %s - Araç Türü: %s - Araç Markası: %s - Araç Modeli: %s\n" +
                        "Kiralama Ücreti: %s - Kiralama Başlangıcı: %s - Kiralama Bitiş Tarihi: %s\n",
                status,
                rentals.getId(),
                rentals.getVehicle().getCategory(),
                rentals.getVehicle().getBrand(),
                rentals.getVehicle().getModel(),
                rentals.getTotalPrice(),
                rentals.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                rentals.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        System.out.println("---------------------");
    }
}
