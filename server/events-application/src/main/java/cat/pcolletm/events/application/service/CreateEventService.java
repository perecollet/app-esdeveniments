package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.CreateEventUseCase;
import cat.pcolletm.events.application.port.in.FillPdfFormUseCase;
import cat.pcolletm.events.application.port.in.JoinEventUseCase;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.DeleteEventPort;
import cat.pcolletm.events.application.port.out.UploadEventPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Date;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateEventService implements CreateEventUseCase {

    private final FillPdfFormUseCase fillPdfFormUseCase;
    private final UploadEventPort uploadEventPort;
    private final DeleteEventPort deleteEventPort;
    private final LoadEventsPort loadEventsPort;
    private final JoinEventUseCase joinEventUseCase;

    @Override
    public boolean createEvent(CreateEventCommand command) {

        Event event = Event.eventWithoutId(
                command.getCreatorId(),
                command.getActivity(),
                command.getDescription(),
                command.getLocation(),
                command.getStartTime(),
                command.getEndTime(),
                command.getNumParticipants(),
                command.getNumEnrolledParticipants(),
                null);

        if (event.getStartTime().before(new Date()) ||
                event.getEndTime().before(event.getStartTime())) return false;

        for (Event e: loadEventsPort.loadEventByLocation(command.getLocation())){
           if (!event.validateEventTime(e))
               return false;
        }

        Long eventId = uploadEventPort.createEvent(event);

        JoinEventUseCase.JoinEventCommand joinCommand = new JoinEventUseCase.JoinEventCommand(eventId, event.getCreatorId().getValue());

        if (!joinEventUseCase.joinEvent(joinCommand)){
            deleteEventPort.deleteEvent(eventId);
            return false;
        }

        fillPdfFormUseCase.fillPdf(eventId, command.getCreatorId().getValue());

        return true;
    }
}
