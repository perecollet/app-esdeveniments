package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.UploadEventPort;
import cat.pcolletm.events.application.port.out.DeleteEventPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class EventPersistenceAdapter implements UploadEventPort, LoadEventsPort, DeleteEventPort {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    @Override
    public Long createEvent(Event event) {
        return eventRepository.save(eventMapper.mapToJpaEnity(event)).getId();
    }

    @Override
    public void updateEvent(Event event){ eventRepository.save(eventMapper.mapToJpaEnity(event));}

    @Override
    public Event loadEventById(Long id) {
        List<User> participants = new ArrayList<>();

        EventJpaEntity event = eventRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        for(UserJpaEntity user: userRepository.findParticipants(id)){
            participants.add(userMapper.mapToDomainEntity(user));
        }

        return eventMapper.mapToDomainEntity(event, participants);
    }

    @Override
    public List<Event> loadEventByLocation(String location) {
        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findByLocation(location)){
            List<User> participants = new ArrayList<>();
            for(UserJpaEntity user: userRepository.findParticipants(event.getId())){
                participants.add(userMapper.mapToDomainEntity(user));
            }
            events.add(eventMapper.mapToDomainEntity(event,participants));
        }

        return events;
    }

    @Override
    public List<Event> loadAllEvents() {

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findAll()){
            List<User> participants = new ArrayList<>();
            for(UserJpaEntity user: userRepository.findParticipants(event.getId())){
                participants.add(userMapper.mapToDomainEntity(user));
            }
            events.add(eventMapper.mapToDomainEntity(event,participants));
        }

        return events;
    }

    @Override
    public List<Event> loadJoinedEvents(Long userId) {

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findByEventsJoinedByUser(userId)){
            List<User> participants = new ArrayList<>();
            for(UserJpaEntity user: userRepository.findParticipants(event.getId())){
                participants.add(userMapper.mapToDomainEntity(user));
            }
            events.add(eventMapper.mapToDomainEntity(event,participants));
        }

        return events;
    }

    @Override
    public List<Event> loadNotJoinedEvents(Long userId) {

        List<Event> events = new ArrayList<>();

        for (EventJpaEntity event: eventRepository.findByEventsNotJoinedByUser(userId)){
            List<User> participants = new ArrayList<>();
            for(UserJpaEntity user: userRepository.findParticipants(event.getId())){
                participants.add(userMapper.mapToDomainEntity(user));
            }
            events.add(eventMapper.mapToDomainEntity(event,participants));
        }

        return events;
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
