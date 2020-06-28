package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.domain.User;

import java.util.List;

public interface LoadUsersPort {

    User loadUserById(Long id);

    User loadByEmail(String email);

    User loadByDni(String dni);

    List<User> loadAll();
}
