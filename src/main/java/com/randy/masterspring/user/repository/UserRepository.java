package com.randy.masterspring.user.repository;

import com.randy.masterspring.error.EntityNotFoundException;
import com.randy.masterspring.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final Map<String,User> userMap = new ConcurrentHashMap<>();

    public User update(String email, User user) throws EntityNotFoundException {
        if (!exists(email)){
            throw new EntityNotFoundException("User " + email + " cannot be fount");
        }
        user.setEmail(email);
        return userMap.put(email,user);
    }

    public User save(User user){
        return userMap.put(user.getEmail(),user);
    }

    public User findOne(String email) throws EntityNotFoundException {
        if (!exists(email)){
            throw new EntityNotFoundException("User " + email + " cannot be fount");
        }
        return userMap.get(email);
    }

    public List<User> findAll(){
        return new ArrayList<>(userMap.values());
    }

    public void delete(String email) throws EntityNotFoundException {
        if (!exists(email)){
            throw new EntityNotFoundException("User " + email + " cannot be fount");
        }
        userMap.remove(email);
    }

    public boolean exists(String email){
        return userMap.containsKey(email);
    }
}
