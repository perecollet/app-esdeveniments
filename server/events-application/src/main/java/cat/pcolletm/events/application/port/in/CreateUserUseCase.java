package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Email;

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


        private String description;

        public CreateUserCommand(String email, String password, String name, String surname, String description){
            this.email = email;
            this.password = password;
            this.name = name;
            this.surname = surname;
            this.description = description;
            this.validateSelf();
        }
    }
}
