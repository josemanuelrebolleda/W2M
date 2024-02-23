package com.W2M.naves.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(NegativeIdAspect.class);

    @Before("execution(* com.example.naves.service.NaveEspacialService.findById(Long)) && args(-id)")
    public void logNegativeId(Long id) {
        if (id < 0) {
            LOGGER.warn("Se ha solicitado una nave con id negativo: {}", id);
        }
    }
}