package cat.pcolletm.events.domain;

import lombok.*;

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

    public static User userWithId(UserId userId, String email, String password, String name, String surnmame,
                                  String dni, String phone, String address, String city, String zipcode, String description) {
        return new User(userId,email,password,name,surnmame,dni,phone,address,city,zipcode,description);
    }

    public static User userWithoutId(String email, String password, String name, String surnmame,
                                     String dni, String phone, String address, String city, String zipcode,String description) {
        return new User(null,email,password,name,surnmame,dni,phone,address,city,zipcode,description);
    }


    @Value
    public static class UserId {
        private long value;
    }
}
