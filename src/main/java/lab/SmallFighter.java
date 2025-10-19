package lab;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class SmallFighter extends EnemyEntity implements HasCollision{
    public SmallFighter(GameScene gameScene, Point2D position) {
        super(gameScene, position);
        this.width = 25;
        this.height = 25;
        setColor(Color.VIOLET);
    }
}
