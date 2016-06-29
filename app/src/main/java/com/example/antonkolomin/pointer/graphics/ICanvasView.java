package com.example.antonkolomin.pointer.graphics;

import com.example.antonkolomin.pointer.Circle.SimpleCircle;

/**
 * Created by AntonKolomin on 29.06.2016.
 */
public interface ICanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String string);
}
