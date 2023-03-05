package visual;

import java.awt.*;
import java.util.Arrays;

public class Shape {

    public static boolean isInShape(int x, int y, int width, int height, Shapes shape) {
        switch (shape) {
            case RECTANGLE -> {
                int rectX = 0;
                int rectY = 0;
                int rectWidth = width - 1;
                int rectHeight = height - 1;

                // Check if the mouse coordinates are inside the rectangle bounds
                return (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight);
            }
            case OVAL -> {
                int ovalX = 0;
                int ovalY = 0;
                int ovalWidth = width - 1;
                int ovalHeight = height - 1;

                // Check if the mouse coordinates are inside the oval bounds
                double dx = (x - (ovalX + ovalWidth / 2.0)) / (ovalWidth / 2.0);
                double dy = (y - (ovalY + ovalHeight / 2.0)) / (ovalHeight / 2.0);
                return (dx * dx + dy * dy <= 1.0);
            }
            case SMOOTH_RIGHT -> {
                int ovalX = width / 2;
                int ovalY = 0;
                int ovalWidth = width / 2;
                int ovalHeight = height - 2;
                int rectX = 0;
                int rectY = 0;
                int rectWidth = width / 2;
                int rectHeight = height - 2;

                // Check if the mouse coordinates are inside the shape bounds
                if (x >= ovalX && x <= ovalX + ovalWidth && y >= ovalY && y <= ovalY + ovalHeight) {
                    // Mouse is inside the right oval
                    double dx = (x - (ovalX + ovalWidth / 2.0)) / (ovalWidth / 2.0);
                    double dy = (y - (ovalY + ovalHeight / 2.0)) / (ovalHeight / 2.0);
                    return (dx * dx + dy * dy <= 1.0);
                } else if (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight)
                    return true;
                else
                    return false;
            }
            case SMOOTH_LEFT -> {
                int ovalX = 0;
                int ovalY = 0;
                int ovalWidth = width / 2;
                int ovalHeight = height - 2;
                int rectX = width / 2;
                int rectY = 0;
                int rectWidth = width / 2;
                int rectHeight = height - 2;

                // Check if the mouse coordinates are inside the shape bounds
                if (x >= ovalX && x <= ovalX + ovalWidth && y >= ovalY && y <= ovalY + ovalHeight) {
                    // Mouse is inside the left oval
                    double dx = (x - (ovalX + ovalWidth / 2.0)) / (ovalWidth / 2.0);
                    double dy = (y - (ovalY + ovalHeight / 2.0)) / (ovalHeight / 2.0);
                    return (dx * dx + dy * dy <= 1.0);
                } else if (x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight)
                    return true; // Mouse is inside the rectangle
                else
                    return false;
            }
            case TRIANGLE -> {
                int[] xPoints = {width / 2, 0, width};
                int[] yPoints = {0, height, height};

                // Check if the mouse coordinates are inside the triangle bounds
                double area = 0.5 * Math.abs((xPoints[1] - xPoints[0]) * (yPoints[2] - yPoints[0]) - (xPoints[2] - xPoints[0]) * (yPoints[1] - yPoints[0]));
                double s = (yPoints[0] * xPoints[1] - xPoints[0] * yPoints[1] + (yPoints[1] - yPoints[0]) * x + (xPoints[0] - xPoints[1]) * y) / (2.0 * area);
                double t = (xPoints[0] * yPoints[2] - yPoints[0] * xPoints[2] + (yPoints[0] - yPoints[2]) * x + (xPoints[2] - xPoints[0]) * y) / (2.0 * area);
                return (s > 0.0 && t > 0.0 && 1.0 - s - t > 0.0);
            }
            case HEXAGON -> {
                int[] xPoints = {width / 4, 3 * width / 4, width, 3 * width / 4, width / 4, 0};
                int[] yPoints = {0, 0, height / 2, height, height, height / 2};

                // Check if the mouse coordinates are inside the triangle bounds
                int maxX = Arrays.stream(xPoints).max().getAsInt();
                int minX = Arrays.stream(xPoints).min().getAsInt();
                int maxY = Arrays.stream(yPoints).max().getAsInt();
                int minY = Arrays.stream(yPoints).min().getAsInt();

                if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                    int intersections = 0;
                    for (int i = 0; i < xPoints.length; i++) {
                        int x1 = xPoints[i];
                        int y1 = yPoints[i];
                        int x2 = xPoints[(i + 1) % xPoints.length];
                        int y2 = yPoints[(i + 1) % xPoints.length];

                        if ((y > y1 && y < y2) || (y > y2 && y < y1)) {
                            double xIntersection = (double) (y - y1) * (double) (x2 - x1) / (double) (y2 - y1) + x1;
                            if (xIntersection <= x) {
                                intersections++;
                            }
                        }
                    }
                    return intersections % 2 != 0;
                } else {
                    return false;
                }
            }
            case STAR -> {
                int[] xPoints = {
                        0,
                        2 * width / 5,
                        width / 2,
                        3 * width / 5,
                        width,
                        7 * width / 10,
                        8 * width / 10,
                        width / 2,
                        2 * width / 10,
                        3 * width / 10};
                int[] yPoints = {
                        2 * height / 5,
                        4 * height / 10,
                        0,
                        4 * height / 10,
                        2 * height / 5,
                        3 * height / 5,
                        height,
                        7 * height / 10,
                        height,
                        3 * height / 5};
                Polygon star = new Polygon(xPoints, yPoints, 10);

                // Check if the mouse coordinates are inside the star bounds
                return star.contains(x, y);
            }
            case TURTLE -> {
                int[] xPoints = {width / 2, width / 2 + width / 5, width, width / 2 + width / 3, width / 2 + 2 * width / 5,
                        width / 2 + width / 4, width / 2, width / 2 - width / 4, width / 2 - 2 * width / 5, width / 2 - width / 3};
                int[] yPoints = {0, height / 3, height / 3, height / 2, height, 2 * height / 3, 4 * height / 5, 2 * height / 3,
                        height, height / 2};
                Polygon turtle = new Polygon(xPoints, yPoints, 10);
                return turtle.contains(x, y);
            }
            default -> {
                return false;
            }
        }
    }

    public static Graphics drawShape(Graphics graphics, Shapes shape, int width, int height,
                                     Color backgroundColor) {
        switch (shape) {
            case RECTANGLE: {
                graphics.setColor(backgroundColor);
                graphics.fillRect(1, 1, width - 2, height - 2);
                break;
            }
            case OVAL: {
                graphics.setColor(backgroundColor);
                graphics.fillOval(1, 1, width - 2, height - 2);
                break;
            }
            case SMOOTH_RIGHT: {
                graphics.setColor(backgroundColor);
                graphics.fillOval(1, 1, width - 2, height - 2);
                graphics.fillRect(1, 1, width / 2, height - 2);
                break;
            }
            case SMOOTH_LEFT: {
                graphics.setColor(backgroundColor);
                graphics.fillOval(1, 1, width - 2, height - 2);
                graphics.fillRect(width / 2 + 1, 1, width / 2, height - 2);
                break;
            }
            case TRIANGLE: {
                graphics.setColor(backgroundColor);
                int[] xPointsInner = {width / 2, 2, width - 2};
                int[] yPointsInner = {2, height - 2, height - 2};
                graphics.fillPolygon(xPointsInner, yPointsInner, 3);
                break;
            }
            case HEXAGON: {
                graphics.setColor(backgroundColor);
                int[] xPointsInner = {width / 4 + 2, 3 * width / 4 - 2, width - 2, 3 * width / 4 - 2, width / 4 + 2, 2};
                int[] yPointsInner = {2, 2, height / 2, height - 2, height - 2, height / 2};
                graphics.fillPolygon(xPointsInner, yPointsInner, 6);
                break;
            }
            case STAR: {
                graphics.setColor(backgroundColor);
                int[] xPointsInner = {
                        1,
                        2 * width / 5,
                        width / 2 - 2,
                        3 * width / 5 - 2,
                        width - 2,
                        7 * width / 10 - 2,
                        8 * width / 10 - 2,
                        width / 2,
                        2 * width / 10 + 1,
                        3 * width / 10 + 1};
                int[] yPointsInner = {
                        2 * height / 5 + 1,
                        4 * height / 10 + 1,
                        1,
                        4 * height / 10 + 1,
                        2 * height / 5 + 1,
                        3 * height / 5,
                        height - 2,
                        7 * height / 10 - 2,
                        height - 2,
                        3 * height / 5};
                graphics.fillPolygon(xPointsInner, yPointsInner, 10);
                break;
            }
            case TURTLE: {
                graphics.setColor(backgroundColor);
                int[] xPointsInner = {width / 2, width / 2 + width / 6, width - 2, width / 2 + width / 4, width / 2 + 2 * width / 6,
                        width / 2 + width / 3, width / 2, width / 2 - width / 3, width / 2 - 2 * width / 6, width / 2 - width / 4};
                int[] yPointsInner = {2, height / 3 + 2, height / 3 + 2, height / 2 + 2, height - 2, 2 * height / 3 + 2,
                        4 * height / 5 - 2, 2 * height / 3 + 2, height - 2, height / 2 + 2};
                graphics.fillPolygon(xPointsInner, yPointsInner, 10);
                break;
            }
            default:
                break;
        }
        return graphics;
    }

    public static Graphics drawShape(Graphics graphics, Shapes shape, int width, int height,
                                     Color backgroundColor, Color borderColor, Color armedColor, boolean isArmed) {
        switch (shape) {
            case RECTANGLE: {
                graphics.setColor(borderColor);
                graphics.fillRect(0, 0, width, height);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                graphics.fillRect(1, 1, width - 2, height - 2);
                break;
            }
            case OVAL: {
                graphics.setColor(borderColor);
                graphics.fillOval(0, 0, width, height);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                graphics.fillOval(1, 1, width - 2, height - 2);
                break;
            }
            case SMOOTH_RIGHT: {
                graphics.setColor(borderColor);
                graphics.fillOval(0, 0, width, height);
                graphics.fillRect(0, 0, width / 2, height);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                graphics.fillOval(1, 1, width - 2, height - 2);
                graphics.fillRect(1, 1, width / 2, height - 2);
                break;
            }
            case SMOOTH_LEFT: {
                graphics.setColor(borderColor);
                graphics.fillOval(0, 0, width, height);
                graphics.fillRect(width / 2, 0, width / 2, height);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                graphics.fillOval(1, 1, width - 2, height - 2);
                graphics.fillRect(width / 2 + 1, 1, width / 2, height - 2);
                break;
            }
            case TRIANGLE: {
                graphics.setColor(borderColor);
                int[] xPoints = {width / 2, 0, width};
                int[] yPoints = {0, height, height};
                graphics.fillPolygon(xPoints, yPoints, 3);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                int[] xPointsInner = {width / 2, 2, width - 2};
                int[] yPointsInner = {2, height - 2, height - 2};
                graphics.fillPolygon(xPointsInner, yPointsInner, 3);
                break;
            }
            case HEXAGON: {
                graphics.setColor(borderColor);
                int[] xPoints = {width / 4, 3 * width / 4, width, 3 * width / 4, width / 4, 0};
                int[] yPoints = {0, 0, height / 2, height, height, height / 2};
                graphics.fillPolygon(xPoints, yPoints, 6);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                int[] xPointsInner = {width / 4 + 2, 3 * width / 4 - 2, width - 2, 3 * width / 4 - 2, width / 4 + 2, 2};
                int[] yPointsInner = {2, 2, height / 2, height - 2, height - 2, height / 2};
                graphics.fillPolygon(xPointsInner, yPointsInner, 6);
                break;
            }
            case STAR: {
                graphics.setColor(borderColor);
                int[] xPoints = {
                        0,
                        2 * width / 5,
                        width / 2,
                        3 * width / 5,
                        width,
                        7 * width / 10,
                        8 * width / 10,
                        width / 2,
                        2 * width / 10,
                        3 * width / 10};
                int[] yPoints = {
                        2 * height / 5,
                        4 * height / 10,
                        0,
                        4 * height / 10,
                        2 * height / 5,
                        3 * height / 5,
                        height,
                        7 * height / 10,
                        height,
                        3 * height / 5};
                graphics.fillPolygon(xPoints, yPoints, 10);
                graphics.setColor(isArmed ? armedColor : backgroundColor);
                // Here create the same star which is a bit smaller so the bigger star looks like a border
                int[] xPointsInner = {
                        1,
                        2 * width / 5,
                        width / 2 - 2,
                        3 * width / 5 - 2,
                        width - 2,
                        7 * width / 10 - 2,
                        8 * width / 10 - 2,
                        width / 2,
                        2 * width / 10 + 1,
                        3 * width / 10 + 1};
                int[] yPointsInner = {
                        2 * height / 5 + 1,
                        4 * height / 10 + 1,
                        1,
                        4 * height / 10 + 1,
                        2 * height / 5 + 1,
                        3 * height / 5,
                        height - 2,
                        7 * height / 10 - 2,
                        height - 2,
                        3 * height / 5};
                graphics.fillPolygon(xPointsInner, yPointsInner, 10);
                break;
            }
            case TURTLE: {
                graphics.setColor(borderColor);
                int[] xPoints = {width / 2, width / 2 + width / 5, width, width / 2 + width / 3, width / 2 + 2 * width / 5,
                        width / 2 + width / 4, width / 2, width / 2 - width / 4, width / 2 - 2 * width / 5, width / 2 - width / 3};
                int[] yPoints = {0, height / 3, height / 3, height / 2, height, 2 * height / 3, 4 * height / 5, 2 * height / 3,
                        height, height / 2};
                graphics.fillPolygon(xPoints, yPoints, 10);

                graphics.setColor(isArmed ? armedColor : backgroundColor);
                int[] xPointsInner = {width / 2, width / 2 + width / 6, width - 2, width / 2 + width / 4, width / 2 + 2 * width / 6,
                        width / 2 + width / 3, width / 2, width / 2 - width / 3, width / 2 - 2 * width / 6, width / 2 - width / 4};
                int[] yPointsInner = {2, height / 3 + 2, height / 3 + 2, height / 2 + 2, height - 2, 2 * height / 3 + 2,
                        4 * height / 5 - 2, 2 * height / 3 + 2, height - 2, height / 2 + 2};
                graphics.fillPolygon(xPointsInner, yPointsInner, 10);
                break;
            }
            default:
                break;
        }
        return graphics;
    }
}
