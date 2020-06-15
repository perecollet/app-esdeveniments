package cat.pcolletm.events.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface EventRepository extends JpaRepository<EventJpaEntity,Long> {

}
