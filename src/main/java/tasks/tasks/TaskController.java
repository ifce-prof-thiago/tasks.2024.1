package tasks.tasks;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final CreateTask createTask;
    private final UpdateTask updateTask;
    private final DeleteTaskById deleteTaskById;
    private final GetTaskById getTaskById;

    public TaskController(
            CreateTask createTask,
            UpdateTask updateTask,
            DeleteTaskById deleteTaskById,
            GetTaskById getTaskById) {
        this.createTask = createTask;
        this.updateTask = updateTask;
        this.deleteTaskById = deleteTaskById;
        this.getTaskById = getTaskById;
    }

    @PostMapping
    public GetTaskById.GetTaskByIdOutput post(@RequestBody CreateTask.CreateTaskInput in) {
        final var id = createTask.execute(in);
        return getById(id);
    }

    @GetMapping("{task_id}")
    public GetTaskById.GetTaskByIdOutput getById(@PathVariable int task_id) {
        return getTaskById.execute(task_id);
    }

    @PutMapping("{task_id}")
    public GetTaskById.GetTaskByIdOutput update(@PathVariable int task_id, @RequestBody UpdateTask.UpdateTaskInput in) {
        final var id = updateTask.execute(task_id, in);
        return getById(task_id);
    }

    @DeleteMapping("{task_id}")
    public DeleteTaskById.DeleteTaskOutput delete(@PathVariable int task_id) {
        return deleteTaskById.execute(task_id);
    }
}









