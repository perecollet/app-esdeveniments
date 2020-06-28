package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.LeaveEventUseCase;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.DeleteEventPort;
import cat.pcolletm.events.application.port.out.LeaveEventPort;
import cat.pcolletm.events.application.port.out.UploadEventPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class LeaveEventService implements LeaveEventUseCase {

    private final LoadEventsPort loadEventsPort;
    private final UploadEventPort uploadEventPort;
    private final DeleteEventPort deleteEventPort;
    private final LeaveEventPort leaveEventPort;

    @Override
    public boolean leaveEvent(LeaveEventCommand command) {
        Event event = loadEventsPort.loadEventById(command.getEventId());

        if(event != null){
            event.setNumEnrolledParticipants(event.getNumEnrolledParticipants()-1);
            if (event.getNumEnrolledParticipants() == 0) {
                deleteEventPort.deleteEvent(command.getEventId());
            }
            uploadEventPort.updateEvent(event);
            leaveEventPort.leaveEvent(command.getEventId(),command.getUserId());
            return true;
        }
        else{ return false;}
    }
}
