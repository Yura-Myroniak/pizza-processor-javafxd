package project.pizza.service;

import project.pizza.model.Pizza;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PizzaFileReader {

    public static List<Pizza> readPizzasFromFile(String filePath) {
        List<Pizza> pizzas = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split("\\s+");
                if (parts.length >= 4) {
                    String name = parts[1];
                    String size = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    pizzas.add(new Pizza(name, size, price));
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
        return pizzas;
    }
}