package cat.pcolletm.events.application.port.out;

import cat.pcolletm.events.domain.User;

public interface CreateUserPort {

    void createUser (User user);
}
