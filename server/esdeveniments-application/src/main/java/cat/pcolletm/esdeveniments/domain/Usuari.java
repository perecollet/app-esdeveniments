package cat.pcolletm.esdeveniments.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Usuari {

    @Getter
    private UsuariId usuariId;

    @NonNull @Getter @Setter
    private String email;

    @NonNull @Getter @Setter
    private String nom;

    @NonNull @Getter @Setter
    private String primerCognom;

    @Getter @Setter
    private String descripcio;


    @Value
    public static class UsuariId{
        private long valeu;
    }
}
