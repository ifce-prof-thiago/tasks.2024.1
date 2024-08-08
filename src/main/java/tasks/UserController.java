package tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("users")
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public CreateUserOutput post(@RequestBody CreateUserInput in) {

        final var id = jdbcTemplate.update(
                "INSERT INTO users(name, email, password) VALUES (?, ?, ?)",
                in.name(),
                in.email(),
                SHA256.execute(in.password())
        );

        return new CreateUserOutput(
                (long) id,
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
