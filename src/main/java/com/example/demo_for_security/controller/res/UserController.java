package com.example.demo_for_security.controller.res;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/add")
    public String userAdd() {
        return "user add !!";
    }

    @GetMapping("/user/update")
    public String userUpdate() {
        return "user update !!";
    }

    @GetMapping("/user/delete/{id}")
    public String userDelete(@PathVariable String id) {
        return "user delete " + id;
    }

    @GetMapping("/user/query")
    public String userQuery() {
        return "user query !!";
    }

}
