package com.Fornalski.cursoFornalski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Fornalski.cursoFornalski.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> { 

}
