package com.project.musicshopdata.DAO;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.project.musicshopentities.entities.Bill;
import com.project.musicshopentities.entities.Person;

public interface BillDAO extends PagingAndSortingRepository<Bill, Long>{
	List<Bill> findAllByPerson(Person person);
}
