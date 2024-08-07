package tasks;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @PostMapping
    public CreateProjectOutput post(@RequestBody CreateProjectInput in) {

        return new CreateProjectOutput(
                1L,
                in.title(),
                in.description(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }

    public record CreateProjectInput(
            String title,
            String description,
            String status,
            LocalDateTime due_date,
            Long assigned_id,
            Long project_id
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









