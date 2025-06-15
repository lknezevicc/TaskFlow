package hr.lknezevic.taskflow.taskflowgui.components;

import hr.lknezevic.taskflow.taskflowgui.observable.TaskFx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TaskListCell extends ListCell<TaskFx> {

    @Override
    protected void updateItem(TaskFx item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Glavni naslov zadatka
            Label titleLabel = new Label(item.getTitle());
            titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

            // Opis zadatka (može biti kraći tekst)
            Label descriptionLabel = new Label(item.getDescription());
            descriptionLabel.setStyle("-fx-text-fill: #555;");

            // Datum i status s desne strane
            Label dueDateLabel = new Label("Due: " + item.getDueDate().toLocalDate());
            Label statusLabel = new Label(item.getStatus().toString());
            statusLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 2 6 2 6; -fx-background-radius: 4;");

            // Spacer između lijeve i desne strane
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            HBox topRow = new HBox(titleLabel, spacer, dueDateLabel);
            topRow.setAlignment(Pos.CENTER_LEFT);
            topRow.setSpacing(10);

            HBox bottomRow = new HBox(descriptionLabel, spacer, statusLabel);
            bottomRow.setAlignment(Pos.CENTER_LEFT);
            bottomRow.setSpacing(10);

            VBox content = new VBox(topRow, bottomRow);
            content.setSpacing(4);
            content.setPadding(new Insets(8));
            content.setStyle("-fx-border-color: #ddd; -fx-border-radius: 4; -fx-background-color: #f9f9f9;");

            setGraphic(content);
        }
    }

}
