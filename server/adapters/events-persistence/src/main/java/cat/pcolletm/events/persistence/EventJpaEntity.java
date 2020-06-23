package cat.pcolletm.events.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String activity;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private int numParticipants;

    @Column
    private int numEnrolledParticipants;
}
