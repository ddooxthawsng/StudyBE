package LoginWithSpringSecurity.SpringSecure;

import LoginWithSpringSecurity.entity.CustomUserDetails;
import LoginWithSpringSecurity.entity.RolesEntity;
import LoginWithSpringSecurity.entity.UserEntity;
import LoginWithSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("login-admin")
    public ResponseEntity<?> authenticateAdmin(@RequestBody UserEntity user){
//		#Tạo chuỗi authentication từ username và password (object LoginRequest - file này chỉ là 1 class bình thường, chứa 2 trường username và password)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
//        #Set chuỗi authentication đó cho UserPrincipal
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity accountLogin = userService.loadUserEntityByUserName(user.getUsername());

        Set<RolesEntity> roles = accountLogin.getRolesEntities();
        boolean isAdmin = false;
        for (RolesEntity role : roles) {
            if (role.getName().equals("ADMIN")) {
                isAdmin = true;
            }
        }
        if (authentication != null && isAdmin) {
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(jwt);
//            #Trả về chuỗi jwt(authentication string)
        }
        return ResponseEntity.ok("Account không hợp lệ");
    }
}
