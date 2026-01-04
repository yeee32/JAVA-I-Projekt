package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion {
    private static Image img;

    private Point2D position;
    private double timer = 0;
    private double lifetime = 0.3; // seconds
    private double size = 30;

    public Explosion(Point2D position) {
        this.position = position;
        img = getImg();
    }

    private Image getImg(){
        if (img == null) {
            img = new Image(SmallFighter.class.getResourceAsStream("exlposion.gif"));
        }
        return img;
    }

    public boolean isFinished() {
        return timer >= lifetime;
    }

    public void simulate(double dt) {
        timer += dt;
        size += 200 * dt; // grow
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(
            img,
            position.getX() - size / 2,
            position.getY() - size / 2,
            size,
            size
        );
    }
}
