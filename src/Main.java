public class Main {

    // SJF process = [arrival time, total cpu time, waiting time, process id]
    static final int[][] SJF_DATASET1 = { {0,3,0,1}, {2,6,0,2}, {4,4,0,3}, {6,5,0,4}, {8,2,0,5} };
    static final int[][] SJF_DATASET2 = { {0,2,0,1}, {1,1,0,2}, {2,8,0,3}, {3,4,0,4}, {4,5,0,5} };
    // RR process = [arrival time, total cpu time, remaining cpu time, waiting time, process id]
    static final int[][] RR_DATASET1 = { {0,3,3,0,1}, {2,6,6,0,2}, {4,4,4,0,3}, {6,5,5,0,4}, {8,2,2,0,5} };
    static final int[][] RR_DATASET2 = { {0,2,2,0,1}, {1,1,1,0,2}, {2,8,8,0,3}, {3,4,4,0,4}, {4,5,5,0,5} };

    public static void main(String[] args) {
        System.out.println("Shortest Job First (SJF) Algorithm:");
        System.out.println("Dataset #1:");
        ShortestJobFirst SJF1 = new ShortestJobFirst(SJF_DATASET1);
        System.out.println("\nDataset #2:");
        ShortestJobFirst SJF2 = new ShortestJobFirst(SJF_DATASET2);
        System.out.println("\nRound-Robin (RR) Algorithm:");
        System.out.println("Dataset #1:");
        RoundRobin RR1 = new RoundRobin(RR_DATASET1);
        System.out.println("\nDataset #2:");
        RoundRobin RR2 = new RoundRobin(RR_DATASET2);
    }
}