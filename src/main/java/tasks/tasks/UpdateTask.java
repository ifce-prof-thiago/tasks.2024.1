package tasks.tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateTask {

    private final JdbcTemplate jdbcTemplate;

    public UpdateTask(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int execute(int task_id, UpdateTaskInput in) {
        return jdbcTemplate.update(
                "UPDATE tasks SET title = ?, status= ?, updated_at = ? WHERE id = ?",
                in.title(),
                in.status(),
                LocalDateTime.now(),
                task_id
        );
    }

    public record UpdateTaskInput(
            String title,
            String status
    ) {
    }
}
