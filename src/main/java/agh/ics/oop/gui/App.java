package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {
    private final GridPane grid = new GridPane();
    private final Scene scene = new Scene(this.grid);
    private final int fieldWidth = 60;
    private final int fieldHeight = 60;
    private final int representationWidth = 30;
    private final int representationHeight = 30;
    private AbstractWorldMap map;

    public void init() {
        try {
            List<MoveDirection> directions = new OptionsParser().parse(getParameters().getRaw().toArray(String[]::new));
            this.map = new GrassField(10);
            List<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(2, 4),
                    new Vector2d(-2, -5)));
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            displayMap();
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void start(Stage primaryStage) {
        primaryStage.setScene(this.scene);
        primaryStage.show();
    }

    private void displayMapBorderPart(int x, int y, int mapX, int mapY) {
        String representation = " ";
        if (x == 0 && y == 0) representation = "y/x";
        else if (x == 0) representation = Integer.toString(mapY);
        else representation = Integer.toString(mapX);

        Label label = new Label(representation);
        this.grid.add(label, x, y, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    private void formatGrid(int maxX, int maxY) {
        for (int y = 0; y <= maxY; y++)
            this.grid.getRowConstraints().add(new RowConstraints(this.fieldHeight));

        for (int x = 0; x <= maxX; x++)
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.fieldWidth));

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
                    VBox vBox = guiElementBox.getGuiRepresentation(this.representationWidth, this.representationHeight);
                    this.grid.add(vBox, x, y, 1, 1);
                    GridPane.setHalignment(vBox, HPos.CENTER);
                    GridPane.setValignment(vBox, VPos.CENTER);
                }
            }
        }

        formatGrid(maxX, maxY);
    }
}
