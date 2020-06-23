package cat.pcolletm.events.web;

import cat.pcolletm.events.application.port.in.CreateUserUseCase;
import cat.pcolletm.events.application.port.in.CreateUserUseCase.CreateUserCommand;
import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.common.WebAdapter;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final CreateUserUseCase createUserUseCase;
    private final LoadUsersPort loadUsersPort;

    @PostMapping("/api/users/register")
    ResponseEntity<?> register (@RequestBody User user){
        CreateUserUseCase.CreateUserCommand command = new CreateUserCommand(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getDescription()
        );

        if (!createUserUseCase.createUser(command)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api/users/login")
    public ResponseEntity<?> auth(Principal principal){
        if(principal==null || principal.getName()==null){
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(
                loadUsersPort.loadByEmail(principal.getName()),
                HttpStatus.OK);
    }

    @GetMapping("/api/users/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(loadUsersPort.loadAll());
    }
}
