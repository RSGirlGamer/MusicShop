package com.project.musicshopdata.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.project.musicshopentities.entities.FloppyDisk;

public interface FloppyDiskDAO extends PagingAndSortingRepository<FloppyDisk, Long>{
	
}
