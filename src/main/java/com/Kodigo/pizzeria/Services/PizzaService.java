package com.Kodigo.pizzeria.Services;

import com.Kodigo.pizzeria.Models.Pizza;
import com.Kodigo.pizzeria.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getDataPizza(){
        return pizzaRepository.findAll();
    }
}
