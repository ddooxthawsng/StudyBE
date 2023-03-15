package service.impl;

import entity.CustomUserDetails;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import repository.impl.UserRepo;
import service.UserService;

public class UserServiceImpl implements UserService  {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUserName(String userName) {
        User user = userRepo.findByUsername(userName);
        if(user == null){
            return null;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
