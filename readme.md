# Belajar Java Spring

Tutorial ini merupakan pengenalan terhadap framework Spring yang di-*manage* oleh Spring Boot.
Kita akan mencoba membuat REST API sederhana.

## Daftar Isi

1. [Inisialisasi](#inisialisasi)
2. [Struktur project](#struktur-project)
    - [Spring Boot Autoconfiguration](#spring-boot-autoconfiguration)
    - [`application.yml`](#applicationyml)
3. [Endpoints](#endpoints)
4. [Controllers](#controllers)
5. [Services](#services)
6. [Repositories](#repositories)

## Inisialisasi

Buka [start.spring.io](https://start.spring.io). Setup yang digunakan pada tutorial ini adalah sebagai berikut:
- Dependencies manager: `Maven`
- Language: `Java`
- Spring Boot: `3.0.1`
- Packaging: `Jar`
- Java: `17`
- Dependencies:
  - `Spring Web`
  - `Lombok`

Pada bagian `Project Metadata`, silakan diisi sesuai dengan keinginan.
Jika semua sudah, klik tombol `Generate`, lalu ekstrak file `zip` yang sudah diunduh.
Kita akan mulai mempelajari struktur project yang telah di-*generate*.

## Struktur Project

```
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── readme.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── azkafadhli
    │   │           └── belajarspringboot
    │   │               └── BelajarspringbootApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── azkafadhli
                    └── belajarspringboot
                        └── BelajarspringbootApplicationTests.java

```

- `pom.xml` : *File* ini berisi semua spesikasi yang dibutuhkan oleh *Maven* untuk *build project*.
- `mvnw` & `mvnw.cmd` : script file yang berguna sebagai wrapper jika Maven belum terinstall
- `application.properties` : konfigurasi project, semua variabel environment project ditulis di file ini, akan diubah menjadi `application.yml` yang mendukung penulisan secara hirarki, lebih detailnya bisa dilihat di [link](https://www.baeldung.com/spring-boot-yaml-vs-properties) ini 
- `BelajarspringbootApplication.java` : *main class* dari project ini
- `BelajarspringbootApplicationTests.java` : sebuah *test class* simpel untuk mengecek apakah aplikasi dapat berjalan dengan lancar

Di bawah folder `src/main/java/com/azkafadhli/belajarspringboot`, akan dibuat *package* lain seperti `controllers`, `services`, `repositories`, `entities`, dan `dtos`.

Setelah semua *dependencies* terunduh, jalankan aplikasi untuk memastikan tidak ada *error*.

### Spring Boot Autoconfiguration

Salah satu keuntungan menggunakan Spring Boot adalah *autoconfoguration*.
Ketika aplikasi dijalankan, Spring Boot akan secara otomatis melakukan konfigurasi sesuai dengan *dependencies* yang ditambahakan,
maupun yang didefinisikan menggunakan annotation atau file `application.properties` (atau `application.yml`, cara menulis konfigurasi di file ini akan dijelaskan lebih lanjut di bagian lain).
Spring Boot akan melakukan *scanning* ke semua *package* dan meregistrasi semua kelas yang dibutuhkan ke dalam sebuah *container*.
Kelas ini disebut dengan *Java Bean*, container dengan sebutan *application context*.

Spring menggunakan pattern *dependency injection*.
Spring Boot juga mengandalkan kemampuan *framework* Spring untuk meng-*inject* dependencies secara otomatis menggunakan anotasi `@Autowired`.
Kelas yang telah teregister di *application.context* akan bisa diinject ke kelas lain menggunakan anotasi `@Autowired`.

### `application.yml`

```yaml
user:
  repository:
    user-id: abcdefghijklmnopqrstuvwxyz1234567890
    username: johndoe
    first-name: john
    last-name: doe
    email: johndoe@example.com
```

Kita akan memasukkan value yang sudah ditulis di file `application.yml` ini ke dalam sebuah kelas.
Di bagian [Repositories](#repositories), kita akan melihat kelas `UserRepository` memiliki anotasi
`@ConfigurationProperties(prefix="user.repository")` dan atribut `userId`, `firstName`, `lastName`, `username`, dan `email`.

## Endpoints

| Method | Endpoint      | Route    |
|--------|---------------|----------|
| GET    | get all users | `/users` |

## Repositories

`com.azkafadhli.belajarspringboot.repositories.IUserRepository`
```java
public interface IUserRepository {
    List<Map<String, String>> getUsers();
}
```
`com.azkafadhli.belajarspringboot.repositories.UserRepository`
```java
@ConfigurationProperties(prefix="user.repository")
@Getter
@Setter
@Repository
public class UserRepository implements IUserRepository{

    private List<Map<String, String>> Users;

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public List<Map<String, String>> getUsers() {
        Map<String, String> user1 = Map.of(
                "id", userId,
                "first_name", firstName,
                "last_name", lastName,
                "username", username,
                "email", email
        );
        return List.of(user1);
    }

}
```

## Services

`com.azkafadhli.belajarspringboot.services.IUserService`
```java
public interface IUserService {
    List<Map<String, String>> getUsers();
}
```
`com.azkafadhli.belajarspringboot.services.UserService`
```java
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public List<Map<String, String>> getUsers() {
        return userRepository.getUsers();
    }
}
```

## Controllers
`com.azkafadhli.belajarspringboot.controllers.UserController`
```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
```