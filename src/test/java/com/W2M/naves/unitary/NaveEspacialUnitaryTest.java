package com.W2M.naves.unitary;

import com.W2M.naves.entity.NaveEspacial;
import com.W2M.naves.repository.NaveEspacialRepository;
import com.W2M.naves.service.NaveEspacialService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NaveEspacialUnitaryTest {

    @MockBean
    private NaveEspacialRepository naveEspacialRepository;

    @Autowired
    private NaveEspacialService naveEspacialService;

    private NaveEspacial naveEspacial;
    private Page<NaveEspacial> naveEspacialPage;

    @Before
    public void setUp() {
        naveEspacial = new NaveEspacial(1L, "X-Wing", "Star Wars");
        naveEspacialPage = new PageImpl<>(List.of(naveEspacial));
    }

    @Test
    public void testFindAll() {
        when(naveEspacialRepository.findAll(any(PageRequest.class))).thenReturn(naveEspacialPage);

        Page<NaveEspacial> result = naveEspacialService.findAll(PageRequest.of(0, 10));

        assertEquals(naveEspacialPage, result);
        verify(naveEspacialRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    public void testFindById() {
        when(naveEspacialRepository.findById(1L)).thenReturn(Optional.of(naveEspacial));

        Optional<NaveEspacial> result = naveEspacialService.findById(1L);

        assertEquals(Optional.of(naveEspacial), result);
        verify(naveEspacialRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByNameContainingIgnoreCase() {
        when(naveEspacialRepository.findByNombreCustom("X", any(PageRequest.class))).thenReturn(naveEspacialPage);

        Page<NaveEspacial> result = naveEspacialService.findByNombre("X", PageRequest.of(0, 10));

        assertEquals(naveEspacialPage, result);
        verify(naveEspacialRepository, times(1)).findByNombreCustom("X", any(PageRequest.class));
    }

    @Test
    public void testSave() {
        when(naveEspacialRepository.save(naveEspacial)).thenReturn(naveEspacial);

        NaveEspacial result = naveEspacialService.save(naveEspacial);

        assertEquals(naveEspacial, result);
        verify(naveEspacialRepository, times(1)).save(naveEspacial);
    }

    @Test
    public void testUpdate() {
        NaveEspacial updatedNaveEspacial = new NaveEspacial(1L, "Y-Wing", "Star Wars");
        when(naveEspacialRepository.findById(1L)).thenReturn(Optional.of(naveEspacial));
        when(naveEspacialRepository.save(naveEspacial)).thenReturn(updatedNaveEspacial);

        NaveEspacial result = naveEspacialService.update(naveEspacial);

        assertEquals(updatedNaveEspacial, result);
        verify(naveEspacialRepository, times(1)).findById(1L);
        verify(naveEspacialRepository, times(1)).save(naveEspacial);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(naveEspacialRepository).deleteById(1L);

        naveEspacialService.deleteById(1L);

        verify(naveEspacialRepository, times(1)).deleteById(1L);
    }
}
