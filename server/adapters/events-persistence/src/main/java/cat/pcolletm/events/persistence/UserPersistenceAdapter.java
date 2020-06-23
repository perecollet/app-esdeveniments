package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.application.port.out.CreateUserPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter implements CreateUserPort, LoadUsersPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(User user) {
        userRepository.save(userMapper.mapToJpaEntity(user));
    }

    @Override
    public User loadByEmail(String email) {
        UserJpaEntity user = userRepository.findByEmail(email).orElse(null);

        if (user != null) return userMapper.mapToDomainEntity(user);
        else return null;
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
