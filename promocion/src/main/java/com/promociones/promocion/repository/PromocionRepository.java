package com.promociones.promocion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.promociones.promocion.model.Promocion;

@Repository

public interface PromocionRepository extends JpaRepository<Promocion,Long>
{

}
