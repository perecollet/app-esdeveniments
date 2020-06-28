package cat.pcolletm.events.application.service;

import cat.pcolletm.events.application.port.in.DeleteUserUseCase;
import cat.pcolletm.events.application.port.in.LoadEventsPort;
import cat.pcolletm.events.application.port.out.DeleteUserPort;
import cat.pcolletm.events.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class DeleteUserService implements DeleteUserUseCase {

    private final LoadEventsPort loadEventsPort;
    private final DeleteUserPort deleteUserPort;

    @Override
    public boolean deleteUser(DeleteUserCommand command) {

        if (!loadEventsPort.loadJoinedEvents(command.getUserId()).isEmpty()) return false;

        deleteUserPort.deleteUser(command.getUserId());

        return true;
    }
}
