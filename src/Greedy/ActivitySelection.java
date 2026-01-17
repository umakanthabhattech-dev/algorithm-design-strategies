package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
leetcode 452. Minimum Number of Arrows to Burst Balloons
Given a set of activities with their start and end times,
select the maximum number of activities that can be performed by a single person,
assuming that a person can only work on a single activity at a time.

 */
public class ActivitySelection {

    static class Activity {
        int start, end;

        Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int maxActivities(Activity[] activities) {
        // 1. Sort activities by end time
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        int count = 1; // first activity is always selected
        int lastEnd = activities[0].end;

        // 2. Select non-overlapping activities
        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= lastEnd) {
                count++;
                lastEnd = activities[i].end;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Activity[] activities = {
                new Activity(1, 2),
                new Activity(3, 4),
                new Activity(0, 6),
                new Activity(5, 7),
                new Activity(8, 9),
                new Activity(5, 9)
        };

        System.out.println("Maximum activities: " + maxActivities(activities));
    }
}
