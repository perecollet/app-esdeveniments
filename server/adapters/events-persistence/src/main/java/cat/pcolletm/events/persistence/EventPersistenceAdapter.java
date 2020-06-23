package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.CreateEventPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    public Event loadEventById(Long id) {

        EventJpaEntity event = eventRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return eventMapper.mapToDomainEntity(event);
    }

    @Override
    public List<Event> loadEventByLocation(String location) {
        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findByLocation(location)){
            events.add(eventMapper.mapToDomainEntity(event));
        }

        return events;
    }

    @Override
    public List<Event> loadAllEvents() {

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findAll()){
            events.add(eventMapper.mapToDomainEntity(event));
        }

        return events;
    }

    @Override
    public List<Event> loadJoinedEvents(Long userId) {

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findByEventsJoinedByUser(userId)){
            events.add(eventMapper.mapToDomainEntity(event));
        }

        return events;
    }

    @Override
    public List<Event> loadNotJoinedEvents(Long userId) {

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findByEventsNotJoinedByUser(userId)){
            events.add(eventMapper.mapToDomainEntity(event));
        }

        return events;
    }
}
