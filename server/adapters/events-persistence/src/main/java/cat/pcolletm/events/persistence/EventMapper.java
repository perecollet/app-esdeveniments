package cat.pcolletm.events.persistence;

import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.Event.EventId;
import cat.pcolletm.events.domain.User.UserId;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event mapToDomainEntity(EventJpaEntity eventJpaEntity){
        return Event.eventWithId(
                new EventId(eventJpaEntity.getId()),
                new UserId(eventJpaEntity.getCreatorId()),
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
                event.getCreatorId().getValue(),
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
