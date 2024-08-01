package tasks;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
