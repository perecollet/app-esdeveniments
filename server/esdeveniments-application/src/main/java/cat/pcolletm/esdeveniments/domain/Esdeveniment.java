package cat.pcolletm.esdeveniments.domain;


import lombok.*;

import java.util.Date;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Esdeveniment {

    @Getter
    private final EsdevenimentId esdevenimentId;

    @NonNull @Getter @Setter
    private String titol;

    @NonNull @Getter @Setter
    private String descripcio;

    @NonNull @Getter @Setter
    private String Localitzacio;

    @NonNull @Getter @Setter
    private Date horaInici;

    public static Esdeveniment crearEsdevenimentSenseId (String titol, String descripcio, String localitzacio, Date horaInici){
        return new Esdeveniment(null,titol,descripcio,localitzacio,horaInici);
    }
    public static Esdeveniment crearEsdevenimentAmbId(EsdevenimentId id, String titol, String descripcio, String localitzacio, Date horaInici){
        return new Esdeveniment(id,titol,descripcio,localitzacio,horaInici);
    }


    /*public void afegirParticipant (Usuari usuari){
        participants.add(usuari);
    }

    public void eliminarParticipant (Usuari usuari){
        participants.
    }*/

    @Value
    public static class EsdevenimentId{
        private long value;
    }
}
