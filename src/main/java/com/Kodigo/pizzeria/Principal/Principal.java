package com.Kodigo.pizzeria.Principal;

import com.Kodigo.pizzeria.Models.Pizza;
import com.Kodigo.pizzeria.Repository.PizzaRepository;

import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private PizzaRepository repo;

    public Principal(PizzaRepository pRepository) {
        this.repo = pRepository;
    }


    public void nuevaPizza(){
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos de la pizza al usuario
        System.out.print("Ingrese el nombre de la pizza: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción de la pizza: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese el precio de la pizza: ");
        double precio = scanner.nextDouble();

        System.out.print("¿Es vegetariana? (true/false): ");
        boolean vegetariana = scanner.nextBoolean();

        System.out.print("¿Es vegana? (true/false): ");
        boolean vegana = scanner.nextBoolean();

        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = scanner.nextBoolean();

        // Crear una nueva pizza
        Pizza pizza = new Pizza();
        pizza.setNombre(nombre);
        pizza.setDescripcion(descripcion);
        pizza.setPrecio(precio);
        pizza.setVegetariana(vegetariana);
        pizza.setVegana(vegana);
        pizza.setDisponible(disponible);

        // Guardar la pizza en la base de datos
        repo.save(pizza);

        // Imprimir todas las pizzas
        System.out.println("Lista de pizzas:");
        for (Pizza p : repo.findAll()) {
            System.out.println("ID: " + p.getIdPizza() + ", Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio());
        }

        // Cerrar el escáner
        scanner.close();
    }
}
