package tasks.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GetUserById {

    private final JdbcTemplate jdbcTemplate;

    public GetUserById(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public GetUserByIdOutput execute(int task_id) {

        final RowMapper<GetUserByIdOutput> rowMapper = (rs, i) -> new GetUserByIdOutput(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );

        return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE id=?",
                rowMapper,
                task_id
        );

    }

    public record GetUserByIdOutput(
            Long id,
            String name,
            String email
    ) {

    }
}
