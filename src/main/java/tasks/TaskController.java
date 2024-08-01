package tasks;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @PostMapping
    public CreateTaskOutput post(@RequestBody CreateTaskInput in) {

        return new CreateTaskOutput(
                1L,
                in.title(),
                in.description(),
                "a fazer",
                in.due_date(),
                in.assigned_id(),
                in.project_id(),
                LocalDateTime.now(),
                LocalDateTime.now()
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

    public record CreateTaskOutput(
            Long id,
            String title,
            String description,
            String status,
            LocalDateTime due_date,
            Long assigned_id,
            Long project_id,
            LocalDateTime created_at,
            LocalDateTime updated_at
    ) {
    }

    @GetMapping("{task_id}")
    public GetTaskByIdOutput getById(@PathVariable Long task_id) {

        return new GetTaskByIdOutput(
                task_id,
                "Implementar autenticação",
                "Implementar sistema de login e registro de usuários. ",
                "a fazer",
                LocalDate.of(2024, 7, 20),
                1L,
                1L,
                LocalDateTime.of(2024, 7, 17, 0, 0, 0),
                LocalDateTime.of(2024, 7, 17, 0, 0, 0)
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









