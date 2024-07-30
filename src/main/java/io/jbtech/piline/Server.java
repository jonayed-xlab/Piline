package io.jbtech.piline;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/v1")
public class Server {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
