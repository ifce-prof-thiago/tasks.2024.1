package tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final JdbcTemplate jdbcTemplate;

    public TaskController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public GetTaskByIdOutput post(@RequestBody CreateTaskInput in) {

        final var id = jdbcTemplate.update(
                "INSERT INTO tasks(title, description, status, due_date, assigned_id, project_id) VALUES (?, ?, ?, ?, ?, ?)",
                in.title(),
                in.description(),
                "a fazer",
                in.due_date(),
                in.assigned_id(),
                in.project_id()
        );

        return getById((long) id);

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

    @GetMapping("{task_id}")
    public GetTaskByIdOutput getById(@PathVariable Long task_id) {

        final RowMapper<GetTaskByIdOutput> rowMapper = (rs, i) -> new GetTaskByIdOutput(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getObject("due_date", LocalDate.class),
                rs.getLong("assigned_id"),
                rs.getLong("project_id"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("updated_at", LocalDateTime.class)
        );

        return jdbcTemplate.queryForObject(
                "SELECT * FROM tasks WHERE id=?",
                rowMapper,
                task_id
        );

    }

    public record GetTaskByIdOutput(
            Long id,
            String title,
            String description,
            String status,
            LocalDate due_date,
            Long assigned_id,
            Long project_id,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {

    }

    @PutMapping("{task_id}")
    public GetTaskByIdOutput update(@PathVariable Long task_id, @RequestBody UpdateTaskInput in) {

        final var id = jdbcTemplate.update(
                "UPDATE tasks SET title = ?, status= ?, updated_at = ? WHERE id = ?",
                in.title(),
                in.status(),
                "a fazer",
                LocalDateTime.now(),
                task_id
        );

        return getById(task_id);

    }

    public record UpdateTaskInput(
            String title,
            String status
    ) {
    }

    @DeleteMapping("{task_id}")
    public DeleteTaskOutput delete(@PathVariable Long task_id) {

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









