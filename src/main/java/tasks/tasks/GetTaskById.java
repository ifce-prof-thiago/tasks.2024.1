package tasks.tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class GetTaskById {

    private final JdbcTemplate jdbcTemplate;

    public GetTaskById(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GetTaskByIdOutput execute(int task_id) {

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
}
