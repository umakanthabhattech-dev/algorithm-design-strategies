package Greedy.JobSequencing;
import java.util.Arrays;

/*
explain the below code with comments
The Job Sequencing Problem is a classic optimization problem where we have a set of jobs,
each with a deadline and a profit associated with it.
The goal is to schedule the jobs in such a way that maximizes the total profit
while ensuring that no two jobs overlap in their time slots and each job is completed by its deadline.
example:
Jobs: J1, J2, J3, J4, J5
Profits: 20, 15, 10, 5, 1
Deadlines: 2, 2, 1, 3, 3
The optimal solution is to select jobs J2, J1, and J4, which yields a total profit of 40.
 */
public class JobSequencing {

    public static void main(String[] args) {

        // Example from Abdul Bari video
        Job[] jobs = {
                new Job(1, 20, 2),
                new Job(2, 15, 2),
                new Job(3, 10, 1),
                new Job(4, 5, 3),
                new Job(5, 1, 3)
        };

        jobSequencing(jobs);
    }

    public static void jobSequencing(Job[] jobs) {

        // Step 1: Sort jobs by decreasing profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find max deadline to create slot array
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Slot array: index = time slot ; value = allocated job id
        int[] slot = new int[maxDeadline + 1];   // 1-based indexing
        Arrays.fill(slot, -1);

        int totalProfit = 0;

        // Step 3: Try to schedule each job at the latest available slot
        for (Job job : jobs) {
            for (int t = job.deadline; t > 0; t--) {
                if (slot[t] == -1) {         // free slot found
                    slot[t] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Print result
        System.out.println("Jobs selected in sequence:");
        for (int t = 1; t <= maxDeadline; t++) {
            if (slot[t] != -1)
                System.out.print("J" + slot[t] + " ");
        }

        System.out.println("\nTotal Profit = " + totalProfit);
    }
}
/*
O/P
Jobs selected in sequence:
J2 J1 J4
Total Profit = 40
 */
