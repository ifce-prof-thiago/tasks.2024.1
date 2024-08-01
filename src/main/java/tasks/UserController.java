package tasks;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("users")
public class UserController {


    @PostMapping
    public CreateUserOutput post(@RequestBody CreateUserInput in) {
        return new CreateUserOutput(
                1L,
                in.name(),
                in.email(),
                SHA256.execute(in.password()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public record CreateUserInput(
            String name,
            String email,
            String password
    ) {
    }

    public record CreateUserOutput(
            Long id,
            String name,
            String email,
            String password,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {
    }

}
