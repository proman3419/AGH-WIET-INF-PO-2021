package agh.ics.oop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SimulationVisualizer {
    private final JFrame jFrame = new JFrame();
    private final int width = 1920;
    private final int height = 1080;
    private final RectangularMap map;
    private final JLabel[][] mapCells;
    private final HashMap<Object, ImageIcon> images = new HashMap<>();

    public SimulationVisualizer(RectangularMap map) {
        this.map = map;
        this.mapCells = new JLabel[this.map.height][this.map.width];
        initJFrame();
        initImageIcons();
    }

    private void initJFrame() {
        int cellSize = Math.min(this.height / this.map.height, this.width / this.map.width);

        int i = 0;
        for (int y = 0; y < this.map.height; y++) {
            for (int x = 0; x < this.map.width; x++) {
                //JLabel cell = new JLabel(String.format("x: %d, y: %d, i: %d", x, (this.map.height-1)-y, i));
                JLabel cell = new JLabel();
                cell.setPreferredSize(new Dimension(cellSize, cellSize));
                this.mapCells[(this.map.height-1)-y][x] = cell;
                this.jFrame.add(cell);
                i++;
            }
        }
        this.jFrame.setSize(this.width, this.height);
        this.jFrame.setLayout(new GridLayout(this.map.height, this.map.width));
        this.jFrame.setVisible(true);
    }

    private void initImageIcons() {
        this.images.put(MapDirection.SOUTH, loadImageIcon("down_idle.png"));
        this.images.put(MapDirection.NORTH, loadImageIcon("up_idle.png"));
        this.images.put(MapDirection.EAST, loadImageIcon("right_idle.png"));
        this.images.put(MapDirection.WEST, loadImageIcon("left_idle.png"));
        this.images.put(null, loadImageIcon("grass_tile.png"));
    }

    private ImageIcon loadImageIcon(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(path);
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image scaledImage = image.getScaledInstance(this.mapCells[0][0].getWidth(), this.mapCells[0][0].getHeight(),
                Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public void update() {
        for (int y = 0; y < this.map.height; y++) {
            for (int x = 0; x < this.map.width; x++) {
                Object object = this.map.objectAt(new Vector2d(x, y));
                if (object == null)
                    this.mapCells[y][x].setIcon(this.images.get(null));
                else if (object instanceof Animal) {
                    this.mapCells[y][x].setIcon(this.images.get(null));
                    this.mapCells[y][x].setIcon(this.images.get(((Animal) object).getOrientation()));
                }
            }
        }
        this.jFrame.update(jFrame.getGraphics());
    }
}
