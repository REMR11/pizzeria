package com.Kodigo.pizzeria.Principal;

import com.Kodigo.pizzeria.Models.Pizza;
import com.Kodigo.pizzeria.Repository.PizzaRepository;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private PizzaRepository repo;

    public Principal(PizzaRepository pRepository) {
        this.repo = pRepository;
    }


    public void nuevaPizza(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menú de Pizzería ---");
            System.out.println("1. Registrar nueva pizza");
            System.out.println("2. Obtener todas las pizzas disponibles");
            System.out.println("3. Buscar pizzas por nombre");
            System.out.println("4. Obtener pizzas vegetarianas");
            System.out.println("5. Obtener pizzas con precio mayor a un valor determinado");
            System.out.println("6. Obtener pizzas que contienen un ingrediente específico en la descripción");
            System.out.println("7. Obtener pizzas en un rango de precios específico");
            System.out.println("8. Obtener pizzas disponibles que sean vegetarianas o veganas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearNuevaPizza();
                    break;
                case 2:
                    obtenerTodasLasPizzas();
                    break;
                case 3:
                    buscarPizzasPorNombre(scanner);
                    break;
                case 4:
                    obtenerPizzasVegetarianas();
                    break;
                case 5:
                    obtenerPizzasConPrecioMayor(scanner);
                    break;
                case 6:
                    obtenerPizzasPorIngrediente(scanner);
                    break;
                case 7:
                    obtenerPizzasEnRangoDePrecios(scanner);
                    break;
                case 8:
                    obtenerPizzasDisponiblesVegetarianasOVeganas();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void crearNuevaPizza(){
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


    }
    private void obtenerTodasLasPizzas() {
        List<Pizza> pizzas = repo.findAll();
        System.out.println("Lista de todas las pizzas:");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }

    private void buscarPizzasPorNombre(Scanner scanner) {
        System.out.print("Ingrese el nombre de la pizza a buscar: ");
        String nombre = scanner.nextLine();
        List<Pizza> pizzas = repo.findByNombreContainingIgnoreCase(nombre);
        System.out.println("Pizzas encontradas:");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }

    private void obtenerPizzasVegetarianas() {
        List<Pizza> pizzas = repo.findByVegetarianaTrue();
        System.out.println("Pizzas vegetarianas:");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }

    private void obtenerPizzasConPrecioMayor(Scanner scanner) {
        System.out.print("Ingrese el precio mínimo: ");
        double precioMinimo = scanner.nextDouble();
        List<Pizza> pizzas = repo.findByPrecioGreaterThan(precioMinimo);
        System.out.println("Pizzas con precio mayor a " + precioMinimo + ":");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }

    private void obtenerPizzasPorIngrediente(Scanner scanner) {
        System.out.print("Ingrese el ingrediente a buscar en la descripción: ");
        String ingrediente = scanner.nextLine();
        List<Pizza> pizzas = repo.findByDescripcionContainingIgnoreCase(ingrediente);
        System.out.println("Pizzas que contienen '" + ingrediente + "' en la descripción:");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }

    private void obtenerPizzasEnRangoDePrecios(Scanner scanner) {
        System.out.print("Ingrese el precio mínimo: ");
        double precioMinimo = scanner.nextDouble();
        System.out.print("Ingrese el precio máximo: ");
        double precioMaximo = scanner.nextDouble();
        List<Pizza> pizzas = repo.findByPrecioBetween(precioMinimo, precioMaximo);
        System.out.println("Pizzas en el rango de precios de " + precioMinimo + " a " + precioMaximo + ":");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }

    private void obtenerPizzasDisponiblesVegetarianasOVeganas() {
        List<Pizza> pizzas = repo.findByDisponibleTrueAndVegetarianaTrueOrVeganaTrue();
        System.out.println("Pizzas disponibles que son vegetarianas o veganas:");
        for (Pizza pizza : pizzas) {
            System.out.println("ID: " + pizza.getIdPizza() + ", Nombre: " + pizza.getNombre() + ", Precio: " + pizza.getPrecio());
        }
    }
}
