package com.pago.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pago.pago.model.Pagos;

@Repository
public interface PagosRepository extends JpaRepository<Pagos, Long>{

    

}
