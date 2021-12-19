package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application implements IAnimalMoveObserver {
    private final GridPane grid = new GridPane();
    private AbstractWorldMap map;
    private ThreadedSimulationEngine threadedSimulationEngine;

    public void init() {
        try {
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(1, 0), new Vector2d(2, 1),
                    new Vector2d(-2, -1)));
            this.map = new GrassField(5);
            this.threadedSimulationEngine = new ThreadedSimulationEngine(this.map, positions);
            this.threadedSimulationEngine.addAnimalMoveObserver(this);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private HBox createUserInterface() {
        TextField inputField = new TextField();
        Button simulateButton = new Button("Simulate");
        HBox userInterface = new HBox(inputField, simulateButton);

        simulateButton.setOnAction(click -> {
            List<MoveDirection> moveDirections = new OptionsParser().parse(inputField.getText().split(" "));
            this.threadedSimulationEngine.setDirections(moveDirections);
            Thread simulationEngineThread = new Thread(this.threadedSimulationEngine);
            simulationEngineThread.start();
        });

        return userInterface;
    }

    public void start(Stage primaryStage) {
        HBox userInterface = createUserInterface();
        VBox appInterface = new VBox(userInterface, this.grid);

        primaryStage.setScene(new Scene(appInterface));
        primaryStage.show();
        displayMap();
    }

    private void displayMapBorderPart(int x, int y, int mapX, int mapY) {
        String representation = " ";
        if (x == 0 && y == 0) representation = "y\\x";
        else if (x == 0) representation = Integer.toString(mapY);
        else representation = Integer.toString(mapX);

        Label label = new Label(representation);
        this.grid.add(label, x, y, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    private void formatGrid(int maxX, int maxY) {
        for (int y = 0; y <= maxY; y++)
            this.grid.getRowConstraints().add(new RowConstraints(60));

        for (int x = 0; x <= maxX; x++)
            this.grid.getColumnConstraints().add(new ColumnConstraints(60));

        this.grid.setGridLinesVisible(false);
        this.grid.setGridLinesVisible(true);
    }

    private void displayMap() {
        if (this.map instanceof GrassField)
            ((GrassField) this.map).updateCorners();

        Vector2d ll = this.map.getLowerLeft();
        Vector2d ur = this.map.getUpperRight();
        int margin = 1; // margin for displaying coordinates at the bordres of the map
        int maxX = ur.x - ll.x + margin;
        int maxY = ur.y - ll.y + margin;

        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                int mapX = ll.x + x - margin;
                int mapY = ur.y - y + margin;

                if (x == 0 || y == 0) {
                    displayMapBorderPart(x, y, mapX, mapY);
                }
                else {
                    GuiElementBox guiElementBox = new GuiElementBox(this.map.objectAt(new Vector2d(mapX, mapY)));
                    VBox vBox = guiElementBox.getGuiRepresentation();
                    this.grid.add(vBox, x, y, 1, 1);
                    GridPane.setHalignment(vBox, HPos.CENTER);
                    GridPane.setValignment(vBox, VPos.CENTER);
                }
            }
        }

        formatGrid(maxX, maxY);
    }

    @Override
    public void animalMoved() {
        Platform.runLater(() -> {
            this.grid.getChildren().clear();
            this.grid.getRowConstraints().clear();
            this.grid.getColumnConstraints().clear();
            displayMap();
        });
    }
}
