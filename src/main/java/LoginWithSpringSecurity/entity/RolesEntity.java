package LoginWithSpringSecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="roles")
@Entity
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Name;

    @ManyToMany(mappedBy = "rolesEntities")
    private List<UserEntity> users = new ArrayList<>();


}
