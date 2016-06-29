package com.example.antonkolomin.pointer.Circle;

import android.graphics.Color;

import com.example.antonkolomin.pointer.logics.GameManager;

import java.util.Random;

/**
 * Created by AntonKolomin on 29.06.2016.
 */
public class EnemyCircle extends SimpleCircle {

    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.rgb(0, 200, 0);
    public static final int RANDOM_SPEED = 5;
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius = 10 + random.nextInt(110 - 10);
        EnemyCircle enemyCircle = new EnemyCircle(x,y,radius,dx,dy);
        enemyCircle.setColor(ENEMY_COLOR);
        return enemyCircle;
    }

    public void setEnemyOrFoodColorDependsOn(MainCircle mainCircle) {
        if (isSmallerThan(mainCircle)){
            setColor(FOOD_COLOR);
        }else setColor(ENEMY_COLOR);
    }

    public boolean isSmallerThan(SimpleCircle circle) {
        if (radius<circle.radius){return true;}
        else return false;
    }

    public void moveOnestep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > GameManager.getWidth() || x < 0){
            dx = -dx;
        }
        if (y > GameManager.getHeight() || y < 0){
            dy = -dy;
        }
    }
}
