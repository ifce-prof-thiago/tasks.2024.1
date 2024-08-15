package tasks.tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteTaskById {

    private final JdbcTemplate jdbcTemplate;

    public DeleteTaskById(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DeleteTaskOutput execute(int task_id) {

        jdbcTemplate.update("DELETE FROM tasks WHERE id=?", task_id);

        return new DeleteTaskOutput(
                "Task deleted successfully"
        );
    }

    public record DeleteTaskOutput(
            String message
    ) {
    }
}
