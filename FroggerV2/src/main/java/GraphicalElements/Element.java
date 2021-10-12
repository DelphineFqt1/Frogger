package GraphicalElements;

import GameCommons.Main;
import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Element implements IFroggerGraphics {

    PApplet processing;

    public Element(PApplet P){
        this.processing = P;
    }

    @Override
    public void create_case(float left, float top, float right, float bottom, float r, float g, float b, float opacity) {
        processing.fill(r,g,b,opacity);
        processing.rectMode(processing.CORNERS);
        processing.rect(left, top, right, bottom);

    }

    @Override
    public void create_background(int width, int height, float r, float g, float b) {
        processing.size(width, height);
        processing.background(r,g,b);
    }

    @Override
    public void create_background_im(int width, int height, String filename) {
        processing.size(width, height);
        PImage image = processing.loadImage(filename);
        image.resize(width, height);
    }

    @Override
    public void show_frog(Frog frog) {
        create_case(frog.getLeft(), frog.getTop(), frog.getRight(), frog.getBottom(), 0, 255, 0, 255);
    }

    @Override
    public void show_cars(ArrayList<Obstacle.Car> cars) {
        for (int i=0; i<cars.size(); i++){
            Obstacle.Car car = cars.get(i);
            show_car(car);
    }}

    @Override
    public void show_trunks(ArrayList<Obstacle.Trunk> trunks) {
            for (int i=0; i<trunks.size(); i++){
                Obstacle.Trunk trunk = trunks.get(i);
                show_trunk(trunk);
        }
    }

    @Override
    public void show_car(Obstacle.Car car) {
        create_case(car.getLeft(), car.getTop(), car.getRight(), car.getBottom(), 255, 0, 0, 255);
    }
    @Override
    public void show_trunk(Obstacle.Trunk trunk) {
        create_case(trunk.getLeft(), trunk.getTop(), trunk.getRight(), trunk.getBottom(), 88, 41, 0, 220);
    }
}
