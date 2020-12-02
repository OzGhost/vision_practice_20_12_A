/*
 * YourAnswer.java
 *
 * Copyright by Axon Ivy (Lucerne), all rights reserved.
 */
package com.company;

import java.util.List;
import java.util.ArrayList;

public class YourAnswer {

    public static boolean isPointInsidePolygon(Point point, List<Point> poly){
        Point[] ps = poly.toArray(new Point[poly.size()+1]);
        ps[poly.size()] = poly.get(0);
        return verticalCheck(point, ps) && horizontalCheck(point, ps);
    }

    private static boolean verticalCheck(Point p, Point[] ps){
        List<CutPoint> joins = verticalCut(p, ps);
        int above = 0;
        int below = 0;
        for (CutPoint j: joins) {
            int c = Double.compare(j.y, (double)p.getY());
            if (c == 0) return true;
            if (c < 0) {
                below++;
            } else {
                above++;
            }
        }
        return (above%2 == 1) && (below%2 == 1);
    }

    private static List<CutPoint> verticalCut(Point p, Point[] ps) {
        List<CutPoint> l = new ArrayList<>();
        for (int i = 0; i < ps.length - 1; i++) {
            CutPoint c = vcut(p, ps[i], ps[i+1]);
            if (isBetween(c, ps[i], ps[i+1])) l.add(c);
        }
        return l;
    }

    private static boolean isBetween(CutPoint c, Point f1, Point f2) {
        if (Double.compare(c.x, (double)f2.getX()) == 0 &&
            Double.compare(c.y, (double)f2.getY()) == 0) return false;
        if (
            (
                Double.compare((double)f1.getX(), c.x) <= 0
                &&
                Double.compare(c.x, (double)f2.getX()) < 0
            )
            &&
            (
                Double.compare((double)f1.getY(), c.y) <= 0
                &&
                Double.compare(c.y, (double)f2.getY()) < 0
            )
        ){
            return true;
        }
        return false;
    }

    private static CutPoint vcut(Point t, Point f1, Point f2) {
        double a = ((double)f2.getY() - (double)f1.getY())
                    / ((double)f2.getX() - (double)f1.getX());
        double b = (double)f1.getY() - (double)f1.getX() * a;
        double x = (double)t.getX();
        double y = x*a + b;
        return new CutPoint(x, y);
    }

    private static boolean horizontalCheck(Point p, Point[] ps){
        List<CutPoint> joins = horizontalCut(p, ps);
        int left = 0;
        int right = 0;
        for (CutPoint j: joins) {
            int c = Double.compare(j.x, (double)p.getX());
            if (c == 0) return true;
            if (c < 0) {
                left++;
            } else {
                right++;
            }
        }
        return (left%2 == 1) && (right%2 == 1);
    }

    private static List<CutPoint> horizontalCut(Point p, Point[] ps) {
        List<CutPoint> l = new ArrayList<>();
        for (int i = 0; i < ps.length - 1; i++) {
            CutPoint c = hcut(p, ps[i], ps[i+1]);
            if (isBetween(c, ps[i], ps[i+1])) l.add(c);
        }
        return l;
    }

    private static CutPoint hcut(Point t, Point f1, Point f2) {
        double a = ((double)f2.getY() - (double)f1.getY())
                    / ((double)f2.getX() - (double)f1.getX());
        double b = (double)f1.getY() - (double)f1.getX() * a;
        double y = (double)t.getY();
        double x = (y - b) / a;
        return new CutPoint(x, y);
    }

    private static class CutPoint {
        private double x;
        private double y;
        CutPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
