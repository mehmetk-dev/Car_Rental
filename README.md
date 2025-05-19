# Araç Kiralama Sistemi (Car Rental System) 🚗✈️

## Proje Hakkında 📝
Bu Java tabanlı araç kiralama sistemi, bireysel ve kurumsal müşterilere araba, motosiklet ve helikopter kiralama hizmeti sunar. Sistemde admin, bireysel müşteri ve kurumsal müşteri olmak üzere 3 farklı kullanıcı tipi bulunmaktadır.

## Teknolojiler 🛠️
- **Programlama Dili:** Java
- **Veritabanı:** PostgreSQL
- **Veri Erişimi:** JDBC

## Özellikler ✨
### Kullanıcı Yönetimi 👥
- Kullanıcı girişi ve kayıt
- Bireysel/Kurumsal müşteri ayrımı
- Yaş doğrulama sistemi

### Araç Yönetimi 🚘
- Araç ekleme/silme/listeleme
- Kategoriye göre filtreleme (Araba, Motosiklet, Helikopter)
- Sayfalama ile araç listeleme

### Kiralama İşlemleri 💰
- Saatlik/Günlük/Haftalık/Aylık kiralama
- Kurumsal müşteriler için özel aylık kiralama
- Araç iade takibi
- Geçmiş kiralama geçmişi

### Admin Paneli 🔐
- Araç yönetimi
- Tüm kiralama işlemlerini görüntüleme

## Veritabanı Şeması 🗃️
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL,
    age INTEGER NOT NULL,
    user_type VARCHAR(50)
);

CREATE TABLE vehicles (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    category VARCHAR(20),
    price NUMERIC,
    rental_rate NUMERIC,
    is_available BOOLEAN DEFAULT true
);

CREATE TABLE rentals (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    vehicle_id INTEGER REFERENCES vehicles(id),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    rental_type VARCHAR(20),
    is_returned BOOLEAN DEFAULT false,
    total_price NUMERIC(15,2)
);
```


## 🔧 Projenin Kurulumu

Projeyi kendi bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyin.

### 1. PostgreSQL Kurulumu ve Veritabanı Oluşturma

- PostgreSQL yüklü değilse [https://www.postgresql.org/download/](https://www.postgresql.org/download/) adresinden indirerek kurun.
- pgAdmin ya da terminal üzerinden aşağıdaki komutu kullanarak yeni bir veritabanı oluşturun:

```sql
CREATE DATABASE arac_kiralama;
```
Ardından projeyle birlikte gelen veritabani_dump.sql dosyasını bu veritabanına aktarın:

````
psql -U postgres -d arac_kiralama -f veritabani_dump.sql
````
### 2. Java Ortamı Kurulumu
Bu proje Java 17 ile geliştirilmiştir. Eğer sisteminizde yüklü değilse Java 17 JDK sürümünü kurun.

### 3. Projeyi Çalıştırma
Projeyi IntelliJ IDEA veya Eclipse gibi bir IDE ile açın.
src klasörü içindeki Main.java veya ana sınıfı bulun.
JDBC bağlantı ayarlarının bulunduğu dosyada (DatabaseUtil) veritabanı bağlantı bilgilerini güncelleyin:
````
String url = "jdbc:postgresql://localhost:5432/arac_kiralama";
String user = "postgres";
String password = "şifreniz";
````
### 4. Gerekli Kütüphaneler
JDBC driver (postgresql-42.x.x.jar) proje içinde tanımlı değilse, https://jdbc.postgresql.org/ adresinden indirip Project Structure -> modules klasörüne ekleyin.

### 5. Giriş Bilgileri (Örnek Kullanıcılar)
Kullanıcı Tipi	Email	Şifre (SHA256 hash)
| Kullanıcı Tipi | Email                                                   | Şifre (SHA256 hash) |
| -------------- | ------------------------------------------------------- | ------------------- |
| Admin          | mehmetkerem                                             | mehmetkerem         |
| Bireysel       | [mehmetkerem8@gmail.com]                                | mehmetkerem         |
| Kurumsal       | [mehmetkerem9@gmail.com]                                | mehmetkerem         |


Not: Şifreler SHA-256 ile hashlenmiştir. Giriş için şifre doğrulama kodunun hash karşılaştırma yapması gerekir.



Umarım beğenirsiniz. Benimle iletişime geçmek için [mehmetkerem2109@gmail.com](mailto:mehmetkerem2109@gmail.com)
