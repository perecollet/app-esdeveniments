package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.CreateEventUseCase;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.CreateEventPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Date;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateEventService implements CreateEventUseCase {

    private final CreateEventPort createEventPort;
    private final LoadEventsPort loadEventsPort;

    @Override
    public boolean createEvent(CreateEventCommand command) {

        Event event = Event.eventWithoutId(command.getActivity(),
                command.getDescription(),
                command.getLocation(),
                command.getStartTime(),
                command.getEndTime(),
                command.getNumParticipants(),
                command.getNumEnrolledParticipants());

        if (event.getStartTime().before(new Date()) ||
                event.getEndTime().before(event.getStartTime())) return false;

        for (Event e: loadEventsPort.loadEventByLocation(command.getLocation())){
           if (!event.validateEventTime(e))
               return false;
        }

        createEventPort.createEvent(event);

        return true;
    }
}
