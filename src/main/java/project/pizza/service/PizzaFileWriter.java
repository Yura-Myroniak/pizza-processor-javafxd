package project.pizza.service;

import project.pizza.model.Pizza;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PizzaFileWriter {

    public static void writePizzasToFile(List<Pizza> pizzas, String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("N Name Size Price");
        int count = 1;
        for (Pizza pizza : pizzas){
            lines.add(count + " " + pizza.toString());
            count++;
        }
        Path outputPath = Paths.get(filePath);
        Files.write(outputPath, lines);
    }
}