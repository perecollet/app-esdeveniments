package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.out.JoinEventPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ParticipantsPersistenceAdapter implements JoinEventPort {

    private final ParticipantsRepository participantsRepository;

    @Override
    public void joinEvent(Long eventId, Long userId) {
        participantsRepository.save(new ParticipantsJpaEntity(null,eventId,userId));
    }
}
