package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


//@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    @Query(value = "select * from person where person.username = :username", nativeQuery = true)
    public Person findUserByUsername(@Param("username") String username);

    @Query(value = "delete * from person where person.username = :username", nativeQuery = true)
    public void deleteUserByUsername(@Param("username") String username);

    @Query(value = "select * from  person where person.password=:password",nativeQuery = true)
    public Person findUserByPassword(@Param("password") String password);

    @Query(value = "select * from  person where person.username=:username and password=:password",nativeQuery = true)
    public Person findByUsernameAndPassword(String username,String password);

//    @Query(value = "select * from person where person.username = :username", nativeQuery = true)
//    public Person findByUserName(String username);

}