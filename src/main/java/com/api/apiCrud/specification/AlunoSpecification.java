package com.api.apiCrud.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.api.apiCrud.domain.Aluno;

public class AlunoSpecification implements Specification<Aluno> {

	private Aluno filter;

	public AlunoSpecification(Aluno filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Aluno> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
		Predicate p = cb.disjunction();

		if (filter.getNome() != null) {
			p.getExpressions().add(cb.like(root.get("nome"), "%" + filter.getNome() + "%"));
		}

		if (filter.getEmail() != null) {
			p.getExpressions().add(cb.like(root.get("email"), "%" + filter.getEmail() + "%"));
		}

		return p;

	}

}
