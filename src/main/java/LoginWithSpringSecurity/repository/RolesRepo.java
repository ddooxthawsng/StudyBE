package LoginWithSpringSecurity.repository;

import LoginWithSpringSecurity.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<RolesEntity,Long> {
}
