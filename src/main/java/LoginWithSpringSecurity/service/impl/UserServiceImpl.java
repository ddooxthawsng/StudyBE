package LoginWithSpringSecurity.service.impl;

import LoginWithSpringSecurity.entity.CustomUserDetails;
import LoginWithSpringSecurity.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import LoginWithSpringSecurity.repository.UserRepo;
import LoginWithSpringSecurity.service.UserService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserEntity loadUserEntityByUserName(String userName) {
        UserEntity user = userRepo.findByUsername(userName);
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserEntityById(Long id) {
        // Kiểm tra xem user có tồn tại trong database không?
        Optional<UserEntity> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(id+"");
        }
        CustomUserDetails userDetails = CustomUserDetails.create(user.get());
        return userDetails;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return CustomUserDetails.create(user);
    }


}
