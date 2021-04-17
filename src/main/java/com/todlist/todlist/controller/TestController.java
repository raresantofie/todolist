package com.todlist.todlist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/test/public")
    public String testPublic() {
        return "public";
    }

    @GetMapping(path = "/test/private")
    public String testPrivate() {
        return "private";
    }

    @GetMapping(path = "/test/manager")
    public String testManager() {
        return "manager";
    }

    @GetMapping(path = "/test/developer")
    public String testDeveloper() {
        return "developer";
    }
}
