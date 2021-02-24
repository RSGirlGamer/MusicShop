package com.project.musicshopdata.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.project.musicshopentities.entities.Person;

public interface PersonDAO extends PagingAndSortingRepository<Person, Long>{
	@Query("SELECT p FROM Person p WHERE p.user = :user AND p.password = :password")
	Person findByUserAndPassword(@Param("user") String user, @Param("password") String password);
}
