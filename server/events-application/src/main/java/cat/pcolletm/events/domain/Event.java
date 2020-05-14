package cat.pcolletm.events.domain;


import lombok.*;

import java.util.Date;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Event {

    @Getter
    private final EventId esdevenimentId;

    @NonNull @Getter @Setter
    private String title;

    @NonNull @Getter @Setter
    private String description;

    @NonNull @Getter @Setter
    private String location;

    @NonNull @Getter @Setter
    private Date date;

    @NonNull @Getter @Setter
    private Date startTime;

    public static Event eventWithoutId (String title, String description, String location, Date date, Date startTime){
        return new Event(null,title,description,location,date,startTime);
    }
    public static Event eventWithId(EventId id, String title, String description, String location,Date date, Date startTime){
        return new Event(id,title,description,location,date,startTime);
    }


    /*public void afegirParticipant (Usuari usuari){
        participants.add(usuari);
    }

    public void eliminarParticipant (Usuari usuari){
        participants.
    }*/

    @Value
    public static class EventId{
        private long value;
    }
}
