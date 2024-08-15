package tasks.projects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("projects")
public class ProjectController {

    private final JdbcTemplate jdbcTemplate;

    public ProjectController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public CreateProjectOutput post(@RequestBody CreateProjectInput in) {

        final var id = jdbcTemplate.update(
                "INSERT INTO projects(name, description) VALUES (?, ?)",
                in.name(),
                in.description()
        );

        return new CreateProjectOutput(
                (long) id,
                in.name(),
                in.description(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }

    public record CreateProjectInput(
            String name,
            String description,
            String status
    ) {
    }

    public record CreateProjectOutput(
            Long id,
            String name,
            String description,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {
    }

    @GetMapping("{project_id}")
    public GetProjectByIdOutput getById(@PathVariable Long project_id) {

        return new GetProjectByIdOutput(
                project_id,
                "App de Tarefas",
                "Desenvolvimento de um aplicativo de tarefas",
                LocalDateTime.of(2024, 7, 17, 0, 0, 0),
                LocalDateTime.of(2024, 7, 17, 0, 0, 0)
        );

    }

    public record GetProjectByIdOutput(
            Long id,
            String name,
            String description,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {

    }


    @PutMapping("{project_id}")
    public UpdateProjectOutput update(@PathVariable Long project_id, @RequestBody UpdateProjectInput in) {

        return new UpdateProjectOutput(
                project_id,
                in.name(),
                in.description(),
                LocalDateTime.of(2024, 7, 17, 0, 0, 0),
                LocalDateTime.now()
        );

    }

    public record UpdateProjectInput(
            String name,
            String description
    ) {
    }

    public record UpdateProjectOutput(
            Long id,
            String name,
            String description,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {
    }

    @DeleteMapping("{project_id}")
    public DeleteProjectOutput delete(@PathVariable Long project_id) {

        return new DeleteProjectOutput(
                "Project deleted successfully"
        );

    }

    public record DeleteProjectOutput(
            String message
    ) {
    }
}









