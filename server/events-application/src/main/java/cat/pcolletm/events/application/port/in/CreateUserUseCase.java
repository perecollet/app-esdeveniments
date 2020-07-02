package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.*;
import org.joda.time.DateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

public interface CreateUserUseCase {

    boolean createUser(CreateUserCommand user);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class CreateUserCommand extends SelfValidating<CreateUserCommand> {

        @NonNull @Email
        private String email;

        @NonNull
        private String password;

        @NonNull
        private String name;

        @NonNull
        private String surname;

        @NonNull
        private Date birthday;

        @NonNull
        private String dni;

        @NonNull @Pattern(regexp = "(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}")
        private String phone;

        @NonNull
        private String address;

        @NonNull
        private String city;

        @NonNull
        private String zipcode;


        private String description;

        public CreateUserCommand(String email, String password, String name, String surname, Date birthday, String dni, String phone, String address, String city, String zipcode, String description){
            this.email = email;
            this.password = password;
            this.name = name;
            this.surname = surname;
            this.birthday = birthday;
            this.dni = dni;
            this.phone = phone;
            this.address = address;
            this.city = city;
            this.zipcode = zipcode;
            this.description = description;
            this.validateSelf();
        }
    }
}
