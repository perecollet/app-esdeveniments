package cat.pcolletm.events.persistence;

import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.Event.EventId;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event mapToDomainEntity(EventJpaEntity eventJpaEntity){
        return Event.eventWithId(
                new EventId(eventJpaEntity.getId()),
                eventJpaEntity.getActivity(),
                eventJpaEntity.getDescription(),
                eventJpaEntity.getLocation(),
                eventJpaEntity.getStartTime(),
                eventJpaEntity.getEndTime(),
                eventJpaEntity.getNumParticipants(),
                eventJpaEntity.getNumEnrolledParticipants()
        );
    }

    public EventJpaEntity mapToJpaEnity (Event event){
        return new EventJpaEntity(
                event.getEventId() == null ? null : event.getEventId().getValue(),
                event.getActivity(),
                event.getDescription(),
                event.getLocation(),
                event.getStartTime(),
                event.getEndTime(),
                event.getNumParticipants(),
                event.getNumEnrolledParticipants()
        );
    }
}
