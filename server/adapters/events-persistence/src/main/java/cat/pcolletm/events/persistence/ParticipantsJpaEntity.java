package cat.pcolletm.events.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PARTICIPANTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantsJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long eventId;

    @Column
    private Long userId;

}
