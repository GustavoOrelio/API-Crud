package com.api.apiCrud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.apiCrud.domain.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {

	@Query(value = "select a from Aluno a where a.nome like %?1%")
	Page<Aluno> findByNome(String nome, Pageable page);
	
	Page<Aluno> findAll(Pageable page);
	
}
