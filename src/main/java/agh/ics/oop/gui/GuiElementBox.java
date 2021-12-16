package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private IMapElement mapElement;

    public GuiElementBox(IMapElement mapElement) {
        this.mapElement = mapElement;
    }

    public VBox getGuiRepresentation(int width, int height) {
        if (this.mapElement == null)
            return new VBox();

        Image image = null;
        try {
            image = new Image(new FileInputStream(this.mapElement.getRepresentationImagePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        Label label = new Label(this.mapElement.toLabelString());

        VBox vBox = new VBox(imageView, label);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }
}
