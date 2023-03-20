package LoginWithSpringSecurity.SpringSecure;

import LoginWithSpringSecurity.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import LoginWithSpringSecurity.service.UserService;

@RestController(value = "/test")
public class TestRestcontroller {
    @Autowired
    UserService userService;

    @GetMapping(value = "/getuser")
    public ResponseEntity getData (@){
        UserEntity newUser = userService.loadUserEntityByUserName("thang");
        return ResponseEntity.ok("PASS");
    }
}