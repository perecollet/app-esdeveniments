package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.domain.Event;
import cat.pcolletm.events.domain.Event.EventId;

import java.util.List;

public interface LoadEventsPort {

    Event loadEvent (EventId id);

    List<Event> loadAllEvents();

}
