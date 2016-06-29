package com.example.antonkolomin.pointer.logics;

import com.example.antonkolomin.pointer.Circle.EnemyCircle;
import com.example.antonkolomin.pointer.Circle.MainCircle;
import com.example.antonkolomin.pointer.Circle.SimpleCircle;
import com.example.antonkolomin.pointer.graphics.CanvasView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AntonKolomin on 28.06.2016.
 */
public class GameManager {
    private MainCircle mainCircle;
    private List<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width=w;
        height=h;
        initMainCircle();
        initEnemyCircle();
    }

    private void initEnemyCircle() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i< 10; i++){
            EnemyCircle circle;
            do {
                circle =  EnemyCircle.getRandomCircle();
            } while (circle.isIntersect(mainCircleArea));
            circles.add(circle);
        }
        calculateAndSetCircleColor();
    }

    private void calculateAndSetCircleColor() {
        for (EnemyCircle circle: circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(width/2,height/2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle circle :circles){
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        checkCollision();
        moveCircle();
    }

    private void checkCollision() {
        SimpleCircle circleForDel = null;
        for (EnemyCircle circle : circles) {
            if (mainCircle.isIntersect(circle)){
                if(circle.isSmallerThan(mainCircle)){
                    mainCircle.growRadius(circle);
                    circleForDel = circle;
                    calculateAndSetCircleColor();
                    break;
                }else {
                    gameEnd("YOU LOSE!");
                    return;
                }
            }
        }
        if (circleForDel != null){
            circles.remove(circleForDel);
        }
        if(circles.isEmpty()){
            gameEnd("YOU WIN!");
        }
    }

    private void gameEnd(String string) {
        canvasView.showMessage(string);
        mainCircle.initRadius();
        initEnemyCircle();
        canvasView.redraw();
    }

    private void moveCircle() {
        for(EnemyCircle circle:circles){
            circle.moveOnestep();
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


}
