package com.prashant.repositories;

import com.prashant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value= "SELECT ID, UNAME, EMAIL, PASSWORD FROM User where UNAME = :UNAME", nativeQuery = true)
    List<User> findUserByUsername(@Param("UNAME") String uname);

}
