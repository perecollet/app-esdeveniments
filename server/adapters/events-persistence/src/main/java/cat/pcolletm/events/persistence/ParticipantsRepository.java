package cat.pcolletm.events.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<ParticipantsJpaEntity,Long> {
}
