package cat.pcolletm.events.web;

import cat.pcolletm.events.application.port.in.CreateEventUseCase;
import cat.pcolletm.events.application.port.in.CreateEventUseCase.CreateEventCommand;
import cat.pcolletm.events.application.port.in.JoinEventUseCase;
import cat.pcolletm.events.application.port.in.JoinEventUseCase.JoinEventCommand;
import cat.pcolletm.events.application.port.in.LeaveEventUseCase;
import cat.pcolletm.events.application.port.in.LeaveEventUseCase.LeaveEventCommand;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.DeleteEventPort;
import cat.pcolletm.events.common.WebAdapter;
import cat.pcolletm.events.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class EventsController {

    private final CreateEventUseCase createEventUseCase;
    private final JoinEventUseCase joinEventUseCase;
    private final LeaveEventUseCase leaveEventUseCase;
    private final LoadEventsPort loadEventsPort;
    private final DeleteEventPort deleteEventPort;

    @PostMapping("api/events/new")
    ResponseEntity<?> createEvent(@RequestBody Event event){

        CreateEventUseCase.CreateEventCommand command = new CreateEventCommand(
                event.getCreatorId(),
                event.getActivity(),
                event.getDescription(),
                event.getLocation(),
                event.getStartTime(),
                event.getEndTime(),
                event.getNumParticipants(),
                0);

        if(!createEventUseCase.createEvent(command)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        
        return new ResponseEntity<>(event,HttpStatus.CREATED);
    }

    @GetMapping("api/events/listAll")
    ResponseEntity<?> listAllEvents(){
        return ResponseEntity.ok(loadEventsPort.loadAllEvents());
    }

    @GetMapping("api/events/listJoined/{userId}")
    ResponseEntity<?> listJoinedEvents(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(loadEventsPort.loadJoinedEvents(userId));
    }

    @GetMapping("api/events/listNotJoined/{userId}")
    ResponseEntity<?> listNotJoinedEvents(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(loadEventsPort.loadNotJoinedEvents(userId));
    }

    @PostMapping("api/events/join/{eventId}/{userId}")
    ResponseEntity<?> join(@PathVariable("eventId") Long eventId, @PathVariable("userId") Long userId){
        JoinEventUseCase.JoinEventCommand command = new JoinEventCommand(eventId, userId);
        if(!joinEventUseCase.joinEvent(command)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("api/events/leave/{eventId}/{userId}")
    ResponseEntity<?> leave(@PathVariable("eventId") Long eventId, @PathVariable("userId") Long userId){
        LeaveEventUseCase.LeaveEventCommand command = new LeaveEventCommand(eventId, userId);
        if(!leaveEventUseCase.leaveEvent(command)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("api/events/delete/{eventId}")
    ResponseEntity<?> delete(@PathVariable("eventId") Long eventId){
        deleteEventPort.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
