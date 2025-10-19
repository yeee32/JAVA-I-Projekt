package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {
    private final GameScene gameScene;
    private static Image image;
    private double scrollSpeed = 100;
    private double offsetY = 0;

    public Background(GameScene gameScene) {
        this.gameScene = gameScene;
        image = getImg();
    }

    private static Image getImg() {
        if (image == null) {
            image = new Image(Background.class.getResourceAsStream("bg_no_border.png"));
        }
        return image;
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        double imgWidth = image.getWidth();
        double imgHeight = image.getHeight();

        double scaledWidth = gameScene.getSize().getWidth();
        double scale = scaledWidth / imgWidth;
        double scaledHeight = imgHeight * scale;

        double y = offsetY % scaledHeight;

        for (int row = -1; row <= 0; row++) {
            gc.drawImage(image, 0, y + row * scaledHeight, scaledWidth, scaledHeight);
        }

        gc.restore();
    }

    public void simulate(double delta) {
        offsetY += scrollSpeed * delta;

        double imgHeight = image.getHeight();
        double scale = gameScene.getSize().getWidth() / image.getWidth();
        double scaledHeight = imgHeight * scale;

        if (offsetY >= scaledHeight) {
            offsetY -= scaledHeight;
        }
    }
}
