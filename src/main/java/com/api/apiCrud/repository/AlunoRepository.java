package com.api.apiCrud.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.apiCrud.domain.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {

}
