package pl.agh.login.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = TestController.PREFIX)
public class TestController {

    static final String PREFIX = "/test";

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> postTest() {
        return ResponseEntity.ok(1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Integer> getUser() {
        return ResponseEntity.ok(2);
    }
}
