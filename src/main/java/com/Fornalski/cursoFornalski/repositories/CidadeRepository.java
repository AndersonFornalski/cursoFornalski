package com.Fornalski.cursoFornalski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Fornalski.cursoFornalski.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> { 

}
