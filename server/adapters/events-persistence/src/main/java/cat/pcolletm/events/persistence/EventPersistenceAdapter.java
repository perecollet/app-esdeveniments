package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.CreateEventPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.Event.EventId;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RequiredArgsConstructor
@PersistenceAdapter
public class EventPersistenceAdapter implements CreateEventPort, LoadEventsPort {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public void createEvent(Event event) {
        eventRepository.save(eventMapper.mapToJpaEnity(event));
    }

    @Override
    public Event loadEvent(EventId id) {

        EventJpaEntity event = eventRepository.findById(id.getValue())
                .orElseThrow(EntityNotFoundException::new);

        return eventMapper.mapToDomainEntity(event);
    }

    @Override
    public List<Event> loadAllEvents() {

        List<EventJpaEntity> eventsJpa = eventRepository.findAll();

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventsJpa){
            events.add(eventMapper.mapToDomainEntity(event));
        }

        return events;
    }
}
