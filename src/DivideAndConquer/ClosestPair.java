package DivideAndConquer;

/*
Closest Pair of Points - HARD
Given a set of points in the 2D plane, find the closest pair of points.
You may assume that there are at least two points in the input.
Implement a function that takes a list of points and returns the distance between the closest pair of points.
example:
Input: [(0, 0), (1, 1), (2, 2), (3, 3)]
Output: 1.4142135623730951 (the distance between (0, 0) and (1, 1))
You may use the Euclidean distance formula to calculate the distance between two points.

brute force approach:
1. Initialize a variable min_distance to a large value.
2. Loop through each point in the list.
3. For each point, loop through the remaining points in the list.
4. Calculate the distance between the two points using the Euclidean distance formula.
5. If the calculated distance is less than min_distance, update min_distance.
time complexity: O(n^2) and space complexity: O(1)

Divide and Conquer approach:
1. Sort the points based on their x-coordinates.
2. Define a recursive function that takes a subset of points and returns the minimum distance.
3. In the recursive function, if the number of points is less than or equal to 3, use the brute force approach to find the minimum distance.
4. Otherwise, divide the points into two halves and recursively find the minimum distance in each half.
5. Find the minimum distance (d) from the two halves.
6. Create a strip of points that are within distance d from the dividing line.
7. Sort the strip based on the y-coordinates.
8. Loop through the points in the strip and for each point,
check only the next 7 points (as per the geometric property) to find the minimum distance in the strip.
time complexity: O(n log n) and space complexity: O(n)
 */
//below is the class definition for ClosestPair

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {

    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Distance between two points
    static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Brute force for small number of points
    static double bruteForce(Point[] points, int l, int r) {
        double min = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    // Find minimum distance in strip
    static double stripClosest(List<Point> strip, double d) {
        strip.sort(Comparator.comparingDouble(p -> p.y));

        double min = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1;
                 j < strip.size() && (strip.get(j).y - strip.get(i).y) < min;
                 j++) {
                min = Math.min(min, distance(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    // Recursive divide and conquer
    static double closestUtil(Point[] points, int l, int r) {
        if (r - l <= 3) {
            return bruteForce(points, l, r);
        }

        int mid = (l + r) / 2;
        Point midPoint = points[mid];

        double dl = closestUtil(points, l, mid);
        double dr = closestUtil(points, mid + 1, r);
        double d = Math.min(dl, dr);

        List<Point> strip = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip.add(points[i]);
            }
        }

        return Math.min(d, stripClosest(strip, d));
    }

    // Main function
    static double closest(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closestUtil(points, 0, points.length - 1);
    }

    // Driver
    public static void main(String[] args) {
        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)
        };

        System.out.println("Closest distance = " + closest(points));
    }
}
