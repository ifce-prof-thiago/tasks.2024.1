package tasks;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@CrossOrigin("*")
public class TesteController {


    @GetMapping
    public Output get() {
        return new Output("Olá");
    }

    public record Output(
            String msg
    ) {
    }

}
