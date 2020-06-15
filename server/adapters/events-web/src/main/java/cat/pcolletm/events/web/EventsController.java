package cat.pcolletm.events.web;

import cat.pcolletm.events.application.port.in.CreateEventUseCase;
import cat.pcolletm.events.application.port.in.CreateEventUseCase.CreateEventCommand;
import cat.pcolletm.events.common.WebAdapter;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class EventsController {

    private final CreateEventUseCase createEventUseCase;

    @PostMapping(path = "/api/events/new")
    @CrossOrigin(origins = "http://localhost:8100")
    ResponseEntity<?> createEvent(@RequestBody Event event){

        CreateEventCommand command = new CreateEventCommand(
                event.getActivity(),
                event.getDescription(),
                event.getLocation(),
                event.getStartTime(),
                event.getEndTime(),
                event.getNumParticipants(),
                1);

        createEventUseCase.createEvent(command);

        return new ResponseEntity<>(event,HttpStatus.CREATED);
    }
}
