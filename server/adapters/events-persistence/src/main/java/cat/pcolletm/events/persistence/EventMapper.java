package cat.pcolletm.events;

import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.Event.EventId;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event mapToDomainEntity(EventJpaEntity eventJpaEntity){
        return Event.eventWithId(
                new EventId(eventJpaEntity.getId()),
                eventJpaEntity.getTitle(),
                eventJpaEntity.getDescription(),
                eventJpaEntity.getLocation(),
                eventJpaEntity.getDate(),
                eventJpaEntity.getStartTime(),
                eventJpaEntity.getEndTime(),
                eventJpaEntity.getNumParticipants(),
                eventJpaEntity.getNumEnrolledParticipants()
        );
    }

    public EventJpaEntity mapToJpaEnity (Event event){
        return new EventJpaEntity(
                event.getEventId() == null ? null : event.getEventId().getValue(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getDate(),
                event.getStartTime(),
                event.getEndTime(),
                event.getNumParticipants(),
                event.getNumEnrolledParticipants()
        );
    }
}
