package com.W2M.naves.service;

import com.W2M.naves.entity.NaveEspacial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.W2M.naves.repository.NaveEspacialRepository;

import java.util.Optional;

public interface NaveEspacialService {

    @Cacheable(value = "naves", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    Page<NaveEspacial> findAll(Pageable pageable);

    @Cacheable(value = "nave-id", key = "#id")
    Optional<NaveEspacial> findById(Long id);

    @Cacheable(value = "naves-nombre", key = "#nombre")
    Page<NaveEspacial> findByNombre(String nombre, Pageable pageable);

    @CachePut(value = "nave-id", key = "#naveEspacial.id")
    NaveEspacial save(NaveEspacial naveEspacial);

    @CachePut(value = "nave-id", key = "#naveEspacial.id")
    NaveEspacial update(NaveEspacial naveEspacial);

    @CacheEvict(value = {"nave-id", "naves", "naves-nombre"}, allEntries = true)
    void deleteById(Long id);
}