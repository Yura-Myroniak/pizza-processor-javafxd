package project.pizza.service;

import project.pizza.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaFilterService {

    public static List<Pizza> filterCheap(List<Pizza> pizzas) {
        List<Pizza> result = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            if (pizza.getPrice() <= 100) {
                result.add(pizza);
            }
        }
        return result;
    }

    public static List<Pizza> filterMedium(List<Pizza> pizzas) {
        List<Pizza> result = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            if (pizza.getPrice() > 100 && pizza.getPrice() <= 300) {
                result.add(pizza);
            }
        }
        return result;
    }

    public static List<Pizza> filterExpensive(List<Pizza> pizzas) {
        List<Pizza> result = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            if (pizza.getPrice() > 300) {
                result.add(pizza);
            }
        }
        return result;
    }
}