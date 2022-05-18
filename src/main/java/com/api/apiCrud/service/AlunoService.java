package com.api.apiCrud.service;

import com.api.apiCrud.domain.Aluno;
import com.api.apiCrud.exception.BadResourceException;
import com.api.apiCrud.exception.ResouceAlreadyExistsException;
import com.api.apiCrud.exception.ResourceNotFoundException;
import com.api.apiCrud.repository.AlunoRepository;
import com.api.apiCrud.specification.AlunoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    private boolean existsById(Long id){
        return alunoRepository.existsById(id);
    }

    public Aluno findById(Long id) throws ResourceNotFoundException {
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        if(aluno != null) {
            throw new ResourceNotFoundException("Aluno não encontrado com o id: " + id);
        }
        else return aluno;
    }

    public List<Aluno> findAll(int pageNumber, int rowPerPage) {
        List<Aluno> aluno = new ArrayList<>();
        alunoRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(aluno::add);
        return aluno;
    }

    public List<Aluno> findAllByNome(String nome, int pageNumber, int rowPerPage) {
        Aluno filter = new Aluno();
        filter.setNome(nome);
        Specification<Aluno> spec = new AlunoSpecification(filter);

        List<Aluno> alunos = new ArrayList<>();
        alunoRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(alunos::add);
        return alunos;
    }

    public Aluno save(Aluno aluno) throws BadResourceException, ResouceAlreadyExistsException {
        if(!StringUtils.isEmpty(aluno.getNome())) {
            if (aluno.getId() != null && existsById(aluno.getId())) {
                throw new ResouceAlreadyExistsException("Aluno com id: " + aluno.getId() + " já existe");
            }
            return alunoRepository.save(aluno);
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar o aluno");
            exc.addErrorMessage("Aluno está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Aluno aluno) throws BadResourceException, ResourceNotFoundException{
        if (!StringUtils.isEmpty(aluno.getNome())){
            if (!existsById(aluno.getId())){
                throw  new ResourceNotFoundException("Aluno não encontrado com o id: " + aluno.getId());
            }
            alunoRepository.save(aluno);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar o aluno");
            exc.addErrorMessage("Aluno está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Aluno não encontrado com o id: " + id);
        }
        else{
            alunoRepository.deleteById(id);
        }
    }

    public Long count() {
        return alunoRepository.count();
    }

}
