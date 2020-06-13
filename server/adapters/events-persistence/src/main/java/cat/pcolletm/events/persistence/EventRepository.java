package cat.pcolletm.events;

import org.springframework.data.jpa.repository.JpaRepository;

interface EventRepository extends JpaRepository<EventJpaEntity,Long> {
}
