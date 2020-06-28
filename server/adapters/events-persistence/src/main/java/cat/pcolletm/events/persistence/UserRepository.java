package cat.pcolletm.events.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface UserRepository extends JpaRepository<UserJpaEntity,Long> {

    Optional<UserJpaEntity> findByEmail(String email);

    @Query("select u from UserJpaEntity u " +
            "where u.id in (select p.userId from ParticipantsJpaEntity p" +
            "   where p.eventId = :eventId)")
    List<UserJpaEntity> findParticipants(@Param("eventId")Long eventId);
}
