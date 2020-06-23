package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.domain.Event;

import java.util.List;

public interface LoadEventsPort {

    Event loadEventById (Long id);

    List<Event> loadEventByLocation (String location);

    List<Event> loadAllEvents();

    List<Event> loadJoinedEvents (Long userId);

    List<Event> loadNotJoinedEvents (Long userId);

}
