package cat.pcolletm.events.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


interface EventRepository extends JpaRepository<EventJpaEntity,Long> {

    List<EventJpaEntity> findByLocation(String location);

    @Query("select e from EventJpaEntity e " +
            "where e.id in (select p.eventId from ParticipantsJpaEntity p" +
            "   where p.userId = :userId)")
    List<EventJpaEntity> findByEventsJoinedByUser(@Param("userId") Long userId);

    @Query("select e from EventJpaEntity e " +
            "where e.id not in (select p.eventId from ParticipantsJpaEntity p" +
            "   where p.userId = :userId)")
    List<EventJpaEntity> findByEventsNotJoinedByUser(@Param("userId") Long userId);
}
