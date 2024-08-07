package tasks;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permissions")
public class PermissionController {

    @PostMapping
    public CreatePermissionOutput post(@RequestBody CreatePermissionInput in) {

        return new CreatePermissionOutput(
                1L,
                in.user_id(),
                in.project_id,
                in.role()
        );

    }

    public record CreatePermissionInput(
            Long user_id,
            Long project_id,
            String role
    ) {
    }

    public record CreatePermissionOutput(
            Long id,
            Long user_id,
            Long project_id,
            String role
    ) {
    }

    @GetMapping("{permission_id}")
    public GetPermissionByIdOutput getById(@PathVariable Long permission_id) {

        return new GetPermissionByIdOutput(
                permission_id,
                1L,
                1L,
                "owner"
        );

    }

    public record GetPermissionByIdOutput(
            Long id,
            Long user_id,
            Long project_id,
            String role
    ) {
    }

    @DeleteMapping("{permission_id}")
    public DeletePermissionOutput delete(@PathVariable Long permission_id) {

        return new DeletePermissionOutput(
                "Permission deleted successfully"
        );

    }

    public record DeletePermissionOutput(
            String message
    ) {
    }

}









