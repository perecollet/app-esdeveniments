package cat.pcolletm.events.domain;


import cat.pcolletm.events.common.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Event {

    @Getter
    private final EventId eventId;

    @NonNull @Getter @Setter
    private String activity;

    @NonNull @Getter @Setter
    private String description;

    @NonNull @Getter @Setter
    private String location;


    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @NonNull @Getter @Setter
    private Date startTime;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @NonNull @Getter @Setter
    private Date endTime;

    @Getter @Setter
    private int numParticipants;

    @Getter @Setter
    private int numEnrolledParticipants;


    public Event(){
        this.eventId = null;
    }

    public static Event eventWithoutId (String activity, String description, String location, Date startTime, Date endTime, int numParticipants, int numEnrolledParticipants){
        return new Event(null,activity,description,location,startTime,endTime,numParticipants, numEnrolledParticipants);
    }

    public static Event eventWithId(EventId id, String activity, String description, String location,Date startTime, Date endTime, int numParticipants, int numEnrolledParticipants){
        return new Event(id,activity,description,location,startTime,endTime,numParticipants,numEnrolledParticipants);
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

    public boolean validateEventTime (Event event){
        if (startTime.equals(event.getStartTime())) return false;
        else if (startTime.before(event.getEndTime()) &&
                startTime.after(event.getStartTime())) return false;
        else if (endTime.before(event.getEndTime()) &&
                endTime.after(event.getStartTime())) return false;
        else return true;
    }
}
