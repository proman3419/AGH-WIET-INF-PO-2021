package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {
    GridPane grid = new GridPane();
    Scene scene = new Scene(this.grid);
    int fieldWidth = 30;
    int fieldHeight = 30;

    public void init() {
        try {
            List<MoveDirection> directions = new OptionsParser().parse(getParameters().getRaw().toArray(String[]::new));
            AbstractWorldMap map = new GrassField(10);
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(2, 10),
                    new Vector2d(-2, -5)));
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            displayMap(map);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void start(Stage primaryStage) {
        primaryStage.setScene(this.scene);
        primaryStage.show();
    }

    private String getRepresentation(AbstractWorldMap map, Vector2d ll, Vector2d ur, int margin, int x, int y) {
        int mapX = ll.x + x - margin;
        int mapY = ur.y - y + margin;

        if (x == 0 && y == 0)
            return "y/x";
        else if (x == 0)
            return Integer.toString(mapY);
        else if (y == 0)
            return Integer.toString(mapX);
        else {
            Object objectAt = map.objectAt(new Vector2d(mapX, mapY));
            return objectAt == null ? "  " : objectAt.toString();
            // return String.format("x: %d, y: %d", mapX, mapY);
        }
    }

    private void formatGrid(int maxX, int maxY) {
        for (int y = 0; y <= maxY; y++)
            this.grid.getRowConstraints().add(new RowConstraints(this.fieldHeight));

        for (int x = 0; x <= maxX; x++)
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.fieldWidth));

        this.grid.setGridLinesVisible(true);
    }

    private void displayMap(AbstractWorldMap map) {
        if (map instanceof GrassField)
            ((GrassField) map).updateCorners();

        Vector2d ll = map.getLowerLeft();
        Vector2d ur = map.getUpperRight();
        int margin = 1; // margin for displaying coordinates
        int maxX = ur.x - ll.x + margin;
        int maxY = ur.y - ll.y + margin;

        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                Label label = new Label(getRepresentation(map, ll, ur, margin, x, y));
                this.grid.add(label, x, y, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }

        formatGrid(maxX, maxY);
    }
}
