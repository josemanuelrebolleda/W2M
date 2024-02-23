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

@Service
public class NaveEspacialServiceImpl implements NaveEspacialService{
    @Autowired
    private NaveEspacialRepository naveEspacialRepository;

    @Cacheable(value = "naves", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<NaveEspacial> findAll(Pageable pageable) {
        return naveEspacialRepository.findAll(pageable);
    }

    @Cacheable(value = "nave-id", key = "#id")
    public Optional<NaveEspacial> findById(Long id) {
        return naveEspacialRepository.findById(id);
    }

    @Cacheable(value = "naves-nombre", key = "#nombre")
    public Page<NaveEspacial> findByNombre(String nombre, Pageable pageable) {
        return naveEspacialRepository.findByNombreCustom(nombre,pageable);
    }

    @CachePut(value = "nave-id", key = "#naveEspacial.id")
    public NaveEspacial save(NaveEspacial naveEspacial) {
        return naveEspacialRepository.save(naveEspacial);
    }

    @CachePut(value = "nave-id", key = "#naveEspacial.id")
    public NaveEspacial update(NaveEspacial naveEspacial) {
        return naveEspacialRepository.save(naveEspacial);
    }

    @CacheEvict(value = {"nave-id", "naves", "naves-nombre"}, allEntries = true)
    public void deleteById(Long id) {
        naveEspacialRepository.deleteById(id);
    }
}