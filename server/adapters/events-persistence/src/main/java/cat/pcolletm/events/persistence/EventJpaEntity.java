package cat.pcolletm.events;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private Date date;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private int numParticipants;

    @Column
    private int numEnrolledParticipants;
}
