package com.randy.masterspring.user.api;

import com.randy.masterspring.error.EntityNotFoundException;
import com.randy.masterspring.user.model.User;
import com.randy.masterspring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> create(@RequestBody User user){
        HttpStatus status = HttpStatus.OK;
        if (!userRepository.exists(user.getEmail())){
            status = HttpStatus.CREATED;
        }
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved,status);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) throws EntityNotFoundException {
        if(!userRepository.exists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User saved = userRepository.update(email,user);
        return new ResponseEntity<User>(saved,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String email) throws EntityNotFoundException {
        if (!userRepository.exists(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
