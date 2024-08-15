package tasks.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserById {

    private final JdbcTemplate jdbcTemplate;

    public DeleteUserById(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DeleteUserOutput execute(int task_id) {

        jdbcTemplate.update("DELETE FROM users WHERE id=?", task_id);

        return new DeleteUserOutput(
                "User deleted successfully"
        );
    }

    public record DeleteUserOutput(
            String message
    ) {
    }
}
