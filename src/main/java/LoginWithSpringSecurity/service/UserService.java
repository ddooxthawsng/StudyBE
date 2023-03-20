package LoginWithSpringSecurity.service;

import LoginWithSpringSecurity.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity loadUserEntityByUserName(String userName);
    UserDetails loadUserEntityById(Long id);
}
