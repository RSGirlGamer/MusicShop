package com.project.musicshopdata.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.project.musicshopentities.entities.Bill;

public interface BillDAO extends PagingAndSortingRepository<Bill, Long>{
	
}
