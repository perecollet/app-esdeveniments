package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.JoinEventUseCase;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.CreateEventPort;
import cat.pcolletm.events.application.port.out.JoinEventPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class JoinEventService implements JoinEventUseCase {

    private final LoadEventsPort loadEventsPort;
    private final CreateEventPort createEventPort;
    private final JoinEventPort joinEventPort;

    @Override
    public boolean joinEvent(JoinEventCommand command) {

        Event event = loadEventsPort.loadEventById(command.getEventId());
        List<Event> events = loadEventsPort.loadJoinedEvents(command.getUserId());

        if(!events.isEmpty()){
            for (Event e: events){
                if(event.getEventId().equals(e.getEventId()) || !event.validateEventTime(e))
                    return false;
            }
        }

        event.setNumEnrolledParticipants(event.getNumEnrolledParticipants()+1);
        createEventPort.createEvent(event);
        joinEventPort.joinEvent(command.getEventId(),command.getUserId());

        return true;
    }

}
