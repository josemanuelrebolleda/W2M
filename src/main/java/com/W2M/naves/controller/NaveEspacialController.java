package com.W2M.naves.controller;

import com.W2M.naves.entity.NaveEspacial;
import com.W2M.naves.service.NaveEspacialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/naves")
public class NaveEspacialController {
    @Autowired
    private NaveEspacialServiceImpl naveEspacialServiceImpl;

    @GetMapping
    public ResponseEntity<Page<NaveEspacial>> findAll(@RequestParam(required = false) String nombre, @PageableDefault Pageable pageable) {
        if (nombre != null) {
            return new ResponseEntity<>(naveEspacialServiceImpl.findByNombre(nombre, pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(naveEspacialServiceImpl.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaveEspacial> findById(@PathVariable Long id) {
        Optional<NaveEspacial> response = naveEspacialServiceImpl.findById(id);
        return response.map(naveEspacial -> new ResponseEntity<>(naveEspacial, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<NaveEspacial> save(@RequestBody NaveEspacial naveEspacial) {
        return new ResponseEntity<>(naveEspacialServiceImpl.save(naveEspacial), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaveEspacial> update(@PathVariable Long id, @RequestBody NaveEspacial naveEspacial) {
        naveEspacial.setId(id);
        return new ResponseEntity<>(naveEspacialServiceImpl.update(naveEspacial), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        naveEspacialServiceImpl.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}