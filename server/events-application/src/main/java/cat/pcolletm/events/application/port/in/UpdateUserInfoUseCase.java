package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

public interface UpdateUserInfoUseCase {

    boolean updateUserInfo (UpdateUserInfoCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class UpdateUserInfoCommand extends SelfValidating<UpdateUserInfoUseCase.UpdateUserInfoCommand> {

        @NonNull
        private Long userId;

        @NonNull
        @Email
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

        public UpdateUserInfoCommand(Long userId,String email, String password, String name, String surname,Date birthday, String dni, String phone, String address, String city, String zipcode, String description){
            this.userId = userId;
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
