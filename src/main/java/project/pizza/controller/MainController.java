package project.pizza.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.pizza.service.PizzaProcessingService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML private Button chooseFileBtn;
    @FXML private ComboBox<String> sortCheapComboBox;
    @FXML private ComboBox<String> sortMediumComboBox;
    @FXML private ComboBox<String> sortExpensiveComboBox;
    @FXML private Button processBtn;
    @FXML private Label statusLabel;

    private final List<File> selectedFiles = new ArrayList<>();

    @FXML
    public void initialize() {
        List<String> sortOptions = List.of("Name", "Size + Name", "Price + Name");
        sortCheapComboBox.getItems().addAll(sortOptions);
        sortMediumComboBox.getItems().addAll(sortOptions);
        sortExpensiveComboBox.getItems().addAll(sortOptions);
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Виберіть файли з піцами");
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (files != null && !files.isEmpty()) {
            selectedFiles.clear();
            selectedFiles.addAll(files);

            StringBuilder names = new StringBuilder();
            for (File f : selectedFiles) {
                names.append(f.getName()).append("; ");
            }
            statusLabel.setText("Обрано файли: " + names);
        } else {
            statusLabel.setText("Файли не обрано.");
        }
    }

    @FXML
    private void handleProcess() {
        if (selectedFiles.isEmpty()
                || sortCheapComboBox.getValue() == null
                || sortMediumComboBox.getValue() == null
                || sortExpensiveComboBox.getValue() == null) {
            statusLabel.setText("Оберіть файли та тип сортування для кожної категорії.");
            return;
        }

        try {
            String outputPath = PizzaProcessingService.processFiles(
                    selectedFiles,
                    sortCheapComboBox.getValue(),
                    sortMediumComboBox.getValue(),
                    sortExpensiveComboBox.getValue()
            );
            statusLabel.setText("Обробка завершена. Файли збережено у: " + outputPath);
        } catch (IOException e) {
            statusLabel.setText("Помилка при записі файлів: " + e.getMessage());
        }
    }
}