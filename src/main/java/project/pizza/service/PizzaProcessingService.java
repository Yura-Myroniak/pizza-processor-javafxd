package project.pizza.service;

import project.pizza.model.Pizza;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PizzaProcessingService {

    public static String processFiles(List<File> files,
                                      String sortCheap,
                                      String sortMedium,
                                      String sortExpensive) throws IOException {

        List<Pizza> allPizzas = new ArrayList<>();
        for (File file : files) {
            allPizzas.addAll(PizzaFileReader.readPizzasFromFile(file.getAbsolutePath()));
        }

        List<Pizza> cheap = PizzaFilterService.filterCheap(allPizzas);
        List<Pizza> medium = PizzaFilterService.filterMedium(allPizzas);
        List<Pizza> expensive = PizzaFilterService.filterExpensive(allPizzas);

        sortList(cheap, sortCheap);
        sortList(medium, sortMedium);
        sortList(expensive, sortExpensive);

        String basePath = files.get(0).getParent();
        String outputDir = basePath + File.separator + "output";
        Path outputPath = Paths.get(outputDir);
        Files.createDirectories(outputPath);
        PizzaFileWriter.writePizzasToFile(cheap, outputDir + "/cheap.txt");
        PizzaFileWriter.writePizzasToFile(medium, outputDir + "/medium.txt");
        PizzaFileWriter.writePizzasToFile(expensive, outputDir + "/expensive.txt");

        return outputDir;
    }

    private static void sortList(List<Pizza> list, String sortType) {
        switch (sortType) {
            case "Name" -> list.sort(
                    Comparator.comparing(Pizza::getName));
            case "Size + Name" -> list.sort(
                    Comparator.comparing(Pizza::getSize).thenComparing(Pizza::getName));
            case "Price + Name" -> list.sort(
                    Comparator.comparingDouble(Pizza::getPrice).thenComparing(Pizza::getName));
        }
    }
}