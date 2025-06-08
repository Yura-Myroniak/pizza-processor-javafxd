module project.pizza {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.pizza to javafx.fxml;
    exports project.pizza;
    exports project.pizza.model;
    opens project.pizza.model to javafx.fxml;
    exports project.pizza.service;
    opens project.pizza.service to javafx.fxml;
    exports project.pizza.controller;
    opens project.pizza.controller to javafx.fxml;
}