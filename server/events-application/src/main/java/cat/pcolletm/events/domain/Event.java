package cat.pcolletm.events.domain;


import cat.pcolletm.events.common.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import cat.pcolletm.events.domain.User.UserId;
import lombok.*;

import java.util.Date;
import java.util.List;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Event {

    @Getter
    private final EventId eventId;

    @NonNull @Getter
    private UserId creatorId;

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

    @Getter @Setter
    private List<User> participants;


    public Event(){
        this.eventId = null;
    }

    public static Event eventWithoutId ( UserId creatorId,String activity, String description, String location, Date startTime, Date endTime, int numParticipants, int numEnrolledParticipants, List participants){
        return new Event(null,creatorId,activity,description,location,startTime,endTime,numParticipants, numEnrolledParticipants, participants);
    }

    public static Event eventWithId(EventId id, UserId creatorId,String activity, String description, String location,Date startTime, Date endTime, int numParticipants, int numEnrolledParticipants, List participants){
        return new Event(id,creatorId,activity,description,location,startTime,endTime,numParticipants,numEnrolledParticipants, participants);
    }


    /*public void afegirParticipant (Usuari usuari){
        participants.add(usuari);
    }

    public void eliminarParticipant (Usuari usuari){
        participants.
    }*/

    public void setCreatorId(Long creatorId){
        this.creatorId = new UserId(creatorId);
    }

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
