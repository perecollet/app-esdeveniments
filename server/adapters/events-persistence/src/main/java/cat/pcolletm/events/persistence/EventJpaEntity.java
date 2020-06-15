package cat.pcolletm.events.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "EVENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventJpaEntity {

    @Id
    @GeneratedValue
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
