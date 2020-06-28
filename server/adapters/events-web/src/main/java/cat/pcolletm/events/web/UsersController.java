package cat.pcolletm.events.web;

import cat.pcolletm.events.application.port.in.CreateUserUseCase;
import cat.pcolletm.events.application.port.in.CreateUserUseCase.CreateUserCommand;
import cat.pcolletm.events.application.port.in.DeleteUserUseCase;
import cat.pcolletm.events.application.port.in.DeleteUserUseCase.DeleteUserCommand;
import cat.pcolletm.events.application.port.in.LoadUsersPort;
import cat.pcolletm.events.application.port.in.UpdateUserInfoUseCase;
import cat.pcolletm.events.application.port.in.UpdateUserInfoUseCase.UpdateUserInfoCommand;
import cat.pcolletm.events.common.WebAdapter;
import cat.pcolletm.events.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserInfoUseCase updateUserInfoUseCase;
    private final LoadUsersPort loadUsersPort;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping("/api/users/register")
    ResponseEntity<?> register (@RequestBody User user){
        CreateUserUseCase.CreateUserCommand command = new CreateUserCommand(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getDni(),
                user.getPhone(),
                user.getAddress(),
                user.getCity(),
                user.getZipcode(),
                user.getDescription()
        );

        if (!createUserUseCase.createUser(command)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/api/users/updateUserInfo/{userId}")
    ResponseEntity<?> updateUserInfo (@PathVariable("userId") Long userId, @RequestBody User user){
        UpdateUserInfoUseCase.UpdateUserInfoCommand command = new UpdateUserInfoCommand(
                userId,
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getDni(),
                user.getPhone(),
                user.getAddress(),
                user.getCity(),
                user.getZipcode(),
                user.getDescription()
        );

        if (!updateUserInfoUseCase.updateUserInfo(command)){
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

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<?> getAllUsers(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(loadUsersPort.loadUserById(userId));
    }

    @DeleteMapping("api/users/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
         DeleteUserUseCase.DeleteUserCommand command = new DeleteUserCommand(userId);
         if (!deleteUserUseCase.deleteUser(command)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
