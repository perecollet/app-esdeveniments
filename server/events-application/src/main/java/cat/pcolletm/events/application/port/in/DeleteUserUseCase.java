package cat.pcolletm.events.application.port.in;

import cat.pcolletm.events.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

public interface DeleteUserUseCase {

    boolean deleteUser(DeleteUserCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeleteUserCommand extends SelfValidating<DeleteUserUseCase.DeleteUserCommand> {

        @NonNull
        private Long userId;

        public DeleteUserCommand (Long userId){
            this.userId = userId;
            this.validateSelf();
        }

    }
}
