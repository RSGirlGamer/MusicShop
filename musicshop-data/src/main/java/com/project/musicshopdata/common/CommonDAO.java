package com.project.musicshopdata.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author Debora Rivas
 *
 * @param <E> Entidad Representativa
 * @param <R> Repositorio Representativo
 */
public class CommonDAO<E, R extends PagingAndSortingRepository<E, Long>> {
	@Autowired
	protected R repository;
	
	public List<E> consultList(int into, int to, String orderBy) {
		Pageable pageable = PageRequest.of(into, to, Sort.by(orderBy));
		Page<E> page = this.repository.findAll(pageable);
		return page.getContent();
	}
	public E save(E entity) {
		return this.repository.save(entity);
	}
	public E update(E entity) {
		return this.repository.save(entity);
	}
	public void delete(E entity) {
		this.repository.delete(entity);
	}
}
