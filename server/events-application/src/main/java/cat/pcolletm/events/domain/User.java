package cat.pcolletm.events.domain;

import cat.pcolletm.events.common.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Getter
    private final UserId userId;

    @NonNull @Getter @Setter
    private String email;

    @NonNull @Getter @Setter
    private String password;

    @NonNull @Getter @Setter
    private String name;

    @NonNull @Getter @Setter
    private String surname;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @NonNull @Getter @Setter
    private Date birthday;

    @NonNull @Getter @Setter
    private String dni;

    @NonNull @Getter @Setter
    private String phone;

    @NonNull @Getter @Setter
    private String address;

    @NonNull @Getter @Setter
    private String city;

    @NonNull @Getter @Setter
    private String zipcode;

    @Getter @Setter
    private String description;

    public User (){ this.userId = null; }

    public static User userWithId(UserId userId, String email, String password, String name, String surnmame,Date birthday,
                                  String dni, String phone, String address, String city, String zipcode, String description) {
        return new User(userId,email,password,name,surnmame,birthday,dni,phone,address,city,zipcode,description);
    }

    public static User userWithoutId(String email, String password, String name, String surnmame,Date birthday,
                                     String dni, String phone, String address, String city, String zipcode,String description) {
        return new User(null,email,password,name,surnmame,birthday,dni,phone,address,city,zipcode,description);
    }

    public boolean checkIsAdult(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formatDate = sdf.format(this.birthday);

        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.parse(formatDate,dtf);

        Period period = Period.between(birthday,now);

        if (period.getYears() < 18){
            return false;
        }

        return true;

    }


    @Value
    public static class UserId {
        private long value;
    }
}
