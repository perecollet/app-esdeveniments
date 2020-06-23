package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import java.util.Date;

public interface CreateEventUseCase {

   boolean createEvent (CreateEventCommand createEventCommand);

   @Value
   @EqualsAndHashCode(callSuper = false)
   class CreateEventCommand extends SelfValidating<CreateEventCommand> {

       @NonNull
       private String activity;

       @NonNull
       private String description;

       @NonNull
       private String location;

       @NonNull
       private Date startTime;

       @NonNull
       private Date endTime;

       private int numParticipants;

       private int numEnrolledParticipants;

       public CreateEventCommand (String activity,String description,String location,Date startTime,
                                  Date endTime, int numParticipants, int numEnrolledParticipants){
           this.activity = activity;
           this.description = description;
           this.location = location;
           this.startTime = startTime;
           this.endTime = endTime;
           this.numParticipants = numParticipants;
           this.numEnrolledParticipants = numEnrolledParticipants;
           this.validateSelf();
       }
   }
}
