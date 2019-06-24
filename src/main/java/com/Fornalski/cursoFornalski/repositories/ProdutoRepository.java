package com.Fornalski.cursoFornalski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Fornalski.cursoFornalski.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> { 

}
