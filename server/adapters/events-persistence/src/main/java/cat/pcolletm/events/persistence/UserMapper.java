package cat.pcolletm.events.persistence;

import cat.pcolletm.events.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToDomainEntity(UserJpaEntity userJpaEntity){
        return User.userWithId(
                new User.UserId(userJpaEntity.getId()),
                userJpaEntity.getEmail(),
                userJpaEntity.getPassword(),
                userJpaEntity.getName(),
                userJpaEntity.getSurname(),
                userJpaEntity.getDescription() == null ? null : userJpaEntity.getDescription()
        );
    }

    public UserJpaEntity mapToJpaEntity (User user){
        return new UserJpaEntity(
                user.getUserId() == null ? null : user.getUserId().getValue(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getDescription()
        );
    }
}
