package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.application.port.in.UpdateUserInfoUseCase;
import cat.pcolletm.events.application.port.out.UploadUserPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.User;
import cat.pcolletm.events.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class UpdateUserInfoService implements UpdateUserInfoUseCase {

    private final UploadUserPort uploadUserPort;
    private final LoadUsersPort loadUsersPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean updateUserInfo(UpdateUserInfoCommand command) {

        if (loadUsersPort.loadUserById(command.getUserId()) == null) return false;

        User user = User.userWithId(
                new UserId(command.getUserId()),
                command.getEmail(),
                passwordEncoder.encode(command.getPassword()),
                command.getName(),
                command.getSurname(),
                command.getBirthday(),
                command.getDni(),
                command.getPhone(),
                command.getAddress(),
                command.getCity(),
                command.getZipcode(),
                command.getDescription()
        );

        uploadUserPort.updateUser(user);

        return true;
    }
}
