package cat.pcolletm.events.application.port.out;

import cat.pcolletm.events.domain.User;

public interface UploadUserPort {

    void createUser (User user);
    void updateUser (User user);
}
