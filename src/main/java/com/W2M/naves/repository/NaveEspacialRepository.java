package com.W2M.naves.repository;

import com.W2M.naves.entity.NaveEspacial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NaveEspacialRepository extends JpaRepository<NaveEspacial, Long> {

    @Query("SELECT n FROM NaveEspacial n WHERE LOWER(n.nombre) LIKE %:nombre%")
    Page<NaveEspacial> findByNombreCustom(@Param("nombre") String nombre, Pageable pageable);
}