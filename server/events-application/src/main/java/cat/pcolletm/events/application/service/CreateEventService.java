package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.CreateEventUseCase;
import cat.pcolletm.events.application.port.out.CreateEventPort;
import cat.pcolletm.events.common.UseCase;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateEventService implements CreateEventUseCase {

    private final CreateEventPort createEventPort;

    @Override
    public boolean createEvent(CreateEventCommand command) {

        Event event = Event.eventWithoutId(command.getActivity(),
                command.getDescription(),
                command.getLocation(),
                command.getStartTime(),
                command.getEndTime(),
                command.getNumParticipants(),
                command.getNumEnrolledParticipants());

        createEventPort.createEvent(event);

        return false;
    }
}
