package cat.pcolletm.events.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Getter
    private UserId userId;

    @Getter @Setter
    private String password;

    @NonNull @Getter @Setter
    private String email;

    @NonNull @Getter @Setter
    private String name;

    @NonNull @Getter @Setter
    private String surname;

    @Getter @Setter
    private String description;


    @Value
    public static class UserId {
        private long value;
    }
}
