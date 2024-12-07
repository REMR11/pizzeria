package com.Kodigo.pizzeria.Repository;

import com.Kodigo.pizzeria.Models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
