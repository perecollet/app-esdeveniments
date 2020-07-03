package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.application.port.out.DeleteEventPort;
import cat.pcolletm.events.application.port.out.DeleteUserPort;
import cat.pcolletm.events.application.port.out.UploadUserPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter implements UploadUserPort, LoadUsersPort, DeleteUserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(User user) {
        userRepository.save(userMapper.mapToJpaEntity(user));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userMapper.mapToJpaEntity(user));
    }

    @Override
    public User loadByEmail(String email) {
        UserJpaEntity user = userRepository.findByEmail(email).orElse(null);

        if (user != null) return userMapper.mapToDomainEntity(user);
        else return null;
    }

    @Override
    public User loadByPhone(String phone) {
        UserJpaEntity user = userRepository.findByPhone(phone).orElse(null);

        if (user != null) return userMapper.mapToDomainEntity(user);
        else return null;
    }

    @Override
    public User loadByDni(String dni) {
        UserJpaEntity user = userRepository.findByDni(dni).orElse(null);

        if (user != null) return userMapper.mapToDomainEntity(user);
        else return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User loadUserById(Long id) {
        UserJpaEntity user = userRepository.findById(id).orElse(null);
        return userMapper.mapToDomainEntity(user);
    }

    @Override
    public List<User> loadAll() {

        List<User> users = new ArrayList<>();

        for (UserJpaEntity user: userRepository.findAll()){
            users.add(userMapper.mapToDomainEntity(user));
        }

        return users;

    }
}
