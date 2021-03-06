package cat.pcolletm.events.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Date birthday;

    @Column
    private String dni;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String zipcode;

    @Column
    private String description;
}
