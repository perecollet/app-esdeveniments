package cat.pcolletm.events.persistence;

import cat.pcolletm.events.application.port.out.JoinEventPort;
import cat.pcolletm.events.application.port.out.LeaveEventPort;
import cat.pcolletm.events.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ParticipantsPersistenceAdapter implements JoinEventPort, LeaveEventPort {

    private final ParticipantsRepository participantsRepository;

    @Override
    public void joinEvent(Long eventId, Long userId) {
        participantsRepository.save(new ParticipantsJpaEntity(null,eventId,userId));
    }

    @Override
    public void leaveEvent(Long eventid, Long userId) {
        participantsRepository.deleteByEventIdAndUserId(eventid,userId);
    }
}
