package cat.pcolletm.events.application.port.out;

import cat.pcolletm.events.domain.Event;

public interface UploadEventPort {

    Long createEvent (Event event);

    void updateEvent (Event event);
}
