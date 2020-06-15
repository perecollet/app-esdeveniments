package cat.pcolletm.events.application.port.out;

import cat.pcolletm.events.domain.Event;

public interface CreateEventPort {

    void createEvent (Event event);

}
