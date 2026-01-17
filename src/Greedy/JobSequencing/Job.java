package Greedy.JobSequencing;

public class Job {
    int id;         // Job number (J1, J2…)
    int profit;     // Profit for completing job
    int deadline;   // Deadline (must finish by this time)

    Job(int id, int profit, int deadline) {
        this.id = id;
        this.profit = profit;
        this.deadline = deadline;
    }
}