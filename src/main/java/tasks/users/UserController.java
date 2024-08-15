package tasks.users;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final CreateUser create;
    private final UpdateUser update;
    private final DeleteUserById delete;
    private final GetUserById get;

    public UserController(
            CreateUser create,
            UpdateUser update,
            DeleteUserById delete,
            GetUserById get) {
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.get = get;
    }

    @PostMapping
    public GetUserById.GetUserByIdOutput post(@RequestBody CreateUser.CreateUserInput in) {
        final var id = create.execute(in);
        return getById(id);
    }

    @GetMapping("{user_id}")
    public GetUserById.GetUserByIdOutput getById(@PathVariable int user_id) {
        return get.execute(user_id);
    }

    @PutMapping("{user_id}")
    public GetUserById.GetUserByIdOutput update(@PathVariable int user_id, @RequestBody UpdateUser.UpdateUserInput in) {
        final var id = update.execute(user_id, in);
        return getById(user_id);
    }

    @DeleteMapping("{user_id}")
    public DeleteUserById.DeleteUserOutput delete(@PathVariable int user_id) {
        return delete.execute(user_id);
    }
}









