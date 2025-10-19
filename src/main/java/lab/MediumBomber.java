package lab;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class MediumBomber extends EnemyEntity implements HasCollision {
    public MediumBomber(GameScene gameScene, Point2D position) {
        super(gameScene, position);
        this.width = 35;
        this.height = 35;
        setColor(Color.PURPLE);
    }
}
