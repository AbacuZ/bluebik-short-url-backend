package com.bluebik.shorturl.controller;

import com.bluebik.shorturl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bluebik.shorturl.domain.User;
import com.bluebik.shorturl.domain.UserReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = {"User Controller"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Get user", notes = "")
    public ResponseEntity findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id", notes = "")
    public ResponseEntity findUserById(
            @ApiParam(value = "user id", required = true)
            @PathVariable Integer id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Add user", notes = "")
    public ResponseEntity addUser(
            @ApiParam(value = "A new user", required = true)
            @RequestBody User user) {
        return new ResponseEntity<>(userService.insert(user), HttpStatus.OK);
    }
    
    @PostMapping("/auth")
    @ApiOperation(value = "Get username", notes = "")
    public ResponseEntity auth(
            @ApiParam(value = "user data", required = true)
            @RequestBody UserReq user) {
        return new ResponseEntity<>(userService.auth(user.getUsername(), user.getPassword()), HttpStatus.OK);
    }

}
