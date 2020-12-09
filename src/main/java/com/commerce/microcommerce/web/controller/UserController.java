package com.commerce.microcommerce.web.controller;

import com.commerce.microcommerce.dao.UserDoa;
import com.commerce.microcommerce.model.User;
import com.commerce.microcommerce.web.Exception.ProduitIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDoa userDoa;

    @GetMapping(value = "/users")
    public List<User> listerUsers() {
        return userDoa.findByDeletedFalse();
    }

    @GetMapping(value = "/allUsers")
    public List<User> listerToutUsers() {
        return userDoa.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User afficherUnutilisateur(@PathVariable int id) throws ProduitIntrouvableException {
        User user= userDoa.findById(id);
        if(user==null) throw new ProduitIntrouvableException("l'utilisateur avec l'id"+id+"nexiste pas");
        return user;

    }


    @DeleteMapping(value = "/users/{id}")
    public User supprimerUnutilisateur(@PathVariable int id) throws ProduitIntrouvableException {
        User user= userDoa.findById(id);
        user.setDeleted(true);
        userDoa.save(user);
        if(user==null) throw new ProduitIntrouvableException("l'utilisateur avec l'id"+id+"nexiste pas");
        return user;

    }

    @PostMapping(value = "/users")
    public ResponseEntity<Void> ajouterutilisateur( @RequestBody User user) {

        User user1 = userDoa.save(user);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user1.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }

    }






