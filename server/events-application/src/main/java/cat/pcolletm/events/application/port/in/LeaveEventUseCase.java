package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

public interface LeaveEventUseCase {

    boolean leaveEvent(LeaveEventCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LeaveEventCommand extends SelfValidating<LeaveEventUseCase.LeaveEventCommand> {

        @NonNull
        private Long eventId;

        @NonNull
        private Long userId;

        public LeaveEventCommand (Long eventId, Long userId){
            this.eventId = eventId;
            this.userId = userId;
            this.validateSelf();
        }

    }
}
