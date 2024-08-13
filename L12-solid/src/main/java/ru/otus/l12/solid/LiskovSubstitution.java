package ru.otus.l12.solid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey created on 09.09.19.
 */
public class LiskovSubstitution {
    private static final Logger logger = LoggerFactory.getLogger(LiskovSubstitution.class);

    /*
    Классический пример нарушения принципа -
        квадрат наследует прямоугольник
     */

    public static void main(String[] args) {
        new LiskovSubstitution().apply();
    }

    private void apply() {
        double height = 2.0;
        double width = 3.0;

        Rectangle rectangle = new Rectangle(); // factory.createRectangle()
        rectangle.setHeight(height);
        rectangle.setWidth(width);

        logger.info("--- Rectangle rectangle = new Rectangle();");
        logger.info("height:{}, rectangle.height:{}", height, rectangle.height);
        logger.info("width:{}, rectangle.width:{}", width, rectangle.width);

        Rectangle rectangleStrange = new Square(); // factory.createRectangle()
        rectangleStrange.setHeight(height);
        rectangleStrange.setWidth(width);

        logger.info("");
        logger.info("--- Rectangle rectangleStrange = new Square();");
        logger.info("height:{}, rectangleStrange.height:{}", height, rectangleStrange.height);
        logger.info("width:{}, rectangleStrange.width:{}", width, rectangleStrange.width);
    }

    private static class Rectangle {
        private double height;
        private double width;

        public double area() {
            return this.height * this.width;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public void setWidth(double width) {
            this.width = width;
        }
    }

    private static class Square extends Rectangle {
        @Override
        public void setHeight(double height) {
            super.setHeight(height);
            super.setWidth(height);
        }

        @Override
        public void setWidth(double width) {
            this.setHeight(width);
        }
    }
}
