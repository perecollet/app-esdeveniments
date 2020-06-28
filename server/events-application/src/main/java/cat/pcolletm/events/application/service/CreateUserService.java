package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.application.port.in.CreateUserUseCase;
import cat.pcolletm.events.application.port.out.UploadUserPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateUserService implements CreateUserUseCase {

    private final UploadUserPort uploadUserPort;
    private final LoadUsersPort loadUsersPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(CreateUserCommand command) {

        if (loadUsersPort.loadByEmail(command.getEmail()) != null ||
                loadUsersPort.loadByDni(command.getDni()) !=null){
            return false;
        }

        User user = User.userWithoutId(
                command.getEmail(),
                passwordEncoder.encode(command.getPassword()),
                command.getName(),
                command.getSurname(),
                command.getDni(),
                command.getPhone(),
                command.getAddress(),
                command.getCity(),
                command.getZipcode(),
                command.getDescription()
        );

        uploadUserPort.createUser(user);

        return true;
    }

}
