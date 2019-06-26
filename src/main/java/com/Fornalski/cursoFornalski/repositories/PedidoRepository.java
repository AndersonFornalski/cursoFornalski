package com.Fornalski.cursoFornalski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Fornalski.cursoFornalski.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> { 

}
