package com.Kodigo.pizzeria.Repository;

import com.Kodigo.pizzeria.Models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findByNombreContainingIgnoreCase(String nombre);

    List<Pizza> findByVegetarianaTrue();

    List<Pizza> findByPrecioGreaterThan(double precioMinimo);

    List<Pizza> findByDescripcionContainingIgnoreCase(String ingrediente);

    List<Pizza> findByPrecioBetween(double precioMinimo, double precioMaximo);

    List<Pizza> findByDisponibleTrueAndVegetarianaTrueOrVeganaTrue();
}
