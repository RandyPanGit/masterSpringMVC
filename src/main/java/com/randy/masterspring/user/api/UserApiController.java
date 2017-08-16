package com.randy.masterspring.user.api;

import com.randy.masterspring.user.model.User;
import com.randy.masterspring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String email, @RequestBody User user){
        return userRepository.save(email,user);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String email){
        userRepository.delete(email);
    }
}
