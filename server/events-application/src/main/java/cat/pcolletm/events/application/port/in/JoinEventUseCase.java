package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

public interface JoinEventUseCase {

    boolean joinEvent(JoinEventCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class JoinEventCommand extends SelfValidating<JoinEventCommand> {

        @NonNull
        private Long eventId;

        @NonNull
        private Long userId;

        public JoinEventCommand (Long eventId, Long userId){
            this.eventId = eventId;
            this.userId = userId;
            this.validateSelf();
        }

    }
}