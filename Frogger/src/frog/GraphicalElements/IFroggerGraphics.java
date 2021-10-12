package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;

import java.util.ArrayList;

public interface IFroggerGraphics {
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b, float opacity);
    abstract void create_background(int width, int height, float r, float g, float b);
    abstract void create_background_im(int width, int height, String filename);
    abstract void show_frog(Frog frog);
    abstract void show_cars(ArrayList<Obstacle.Car> cars);
    abstract void show_trunks(ArrayList<Obstacle.Trunk> trunks);
    abstract void show_car(Obstacle.Car car);
    abstract void show_trunk(Obstacle.Trunk trunk);
}
