package tasks.tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateTask {

    private final JdbcTemplate jdbcTemplate;

    public CreateTask(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int execute(CreateTaskInput in) {
        return jdbcTemplate.update(
                "INSERT INTO tasks(title, description, status, due_date, assigned_to, project_id) VALUES (?, ?, ?, ?, ?, ?)",
                in.title(),
                in.description(),
                "a fazer",
                in.due_date(),
                in.assigned_id(),
                in.project_id()
        );
    }

    public record CreateTaskInput(
            String title,
            String description,
            String status,
            LocalDateTime due_date,
            Long assigned_id,
            Long project_id
    ) {
    }
}
