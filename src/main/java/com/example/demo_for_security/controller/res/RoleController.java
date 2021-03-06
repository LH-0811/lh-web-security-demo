package com.example.demo_for_security.controller.res;

import com.lhit.starter.security.defense.annotation.HasPermsCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @HasPermsCode({"system_role_add_interface"})
    @GetMapping("/role/add")
    public String roleAdd() {
        return "role add !!";
    }

    @GetMapping("/role/update")
    public String roleUpdate() {
        return "role update !!";
    }

    @GetMapping("/role/delete/{id}")
    public String roleDelete(@PathVariable String id) {
        return "role delete " + id;
    }

    @GetMapping("/role/query")
    public String roleQuery() {
        return "role query !!";
    }


}
