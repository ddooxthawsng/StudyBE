package LoginWithSpringSecurity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Table(name="user")
@Data // lombok
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolesEntity> rolesEntities ;

    @Override
    public String toString(){
        return "USER-name: "+username+"Password: "+password;
    }
}