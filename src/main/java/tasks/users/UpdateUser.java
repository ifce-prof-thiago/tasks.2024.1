package tasks.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tasks.SHA256;

import java.time.LocalDateTime;

@Component
public class UpdateUser {

    private final JdbcTemplate jdbcTemplate;

    public UpdateUser(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int execute(int user_id, UpdateUserInput in) {
        return jdbcTemplate.update(
                "UPDATE users SET name = ?, email= ?, password=?, updated_at = ? WHERE id = ?",
                in.name(),
                in.email(),
                SHA256.execute(in.password()),
                LocalDateTime.now(),
                user_id
        );
    }

    public record UpdateUserInput(
            String name,
            String email,
            String password
    ) {
    }
}
