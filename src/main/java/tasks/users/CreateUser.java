package tasks.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tasks.SHA256;

@Component
public class CreateUser {

    private final JdbcTemplate jdbcTemplate;

    public CreateUser(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int execute(CreateUserInput in) {
        return jdbcTemplate.update(
                "INSERT INTO users(name, email, password) VALUES (?, ?, ?)",
                in.name(),
                in.email(),
                SHA256.execute(in.password())
        );
    }

    public record CreateUserInput(
            String name,
            String email,
            String password
    ) {
    }
}
