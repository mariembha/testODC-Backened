package com.commerce.microcommerce.dao;

import com.commerce.microcommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface UserDoa extends JpaRepository<User, Integer> {
    User findById(int id);

    List<User> findByDeletedFalse();

    //List<Product> findByPrixGreaterThan(int prixLimit);
   // @Query("SELECT id , nom , prix From Product p WHERE p.prix> : prixlimit")
    //List<Product> chercherUnproduitCher(@Param("prixLimit") int prix);

    }



