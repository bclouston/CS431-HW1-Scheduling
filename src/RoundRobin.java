import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {

    public RoundRobin(int[][] pList) {
        final int q = 2;
        Queue<int[]> ready = new LinkedList<int[]>();
        int time = 0;

        while (true) {
            // check if a process should be moved to ready queue
            for (int[] process : pList) {
                if (process[0] == time || process[0] == time - 1) {
                    if (!ready.contains(process)) {
                        ready.add(process);
                    }
                }
            }
            if (!ready.isEmpty()) {
                // remaining CPU time greater than one time quantum (q)
                if (ready.element()[2] > q) {
                    time += q;
                    ready.element()[2] -= q;
                    // return process from front to end of ready queue
                    ready.add(ready.poll());
                }
                // remaining CPU time less than one time quantum (q)
                else {
                    time = time + ready.element()[2];
                    // calculate waiting time: current time - arrival time - cpu time used
                    ready.element()[3] = time - ready.element()[0] - ready.element()[1];
                    ready.element()[2] = 0;
                    // copy completed process back to list
                    pList[ready.element()[4] - 1] = ready.poll();
                }
            }
            // nothing left in ready queue, all processes complete
            else {
                break;
            }
        }
        // print results and calculate averages
        int waitTotal = 0, tatTotal = 0;
        System.out.println("+----+--------------+----------+--------------+-----------------+");
        System.out.printf("| %-1s | %-12s | %-8s | %-12s | %-15s |", "ID", "Arrival Time", "CPU Time", "Waiting Time", "Turnaround Time");
        System.out.println("\n+----+--------------+----------+--------------+-----------------+");
        for (int[] process : pList) {
            waitTotal += process[3];
            tatTotal += process[1] + process[3];
            System.out.printf("| %-2s | %-12s | %-8s | %-12s | %-15s |\n",
                    process[4], process[0], process[1], process[3], process[1] + process[3]);
        }
        System.out.println("+----+--------------+----------+--------------+-----------------+");
        float avgWaitTime = (float) waitTotal / pList.length;
        float avgTurnAround = (float) tatTotal / pList.length;
        System.out.println("Average Wait Time: " + avgWaitTime);
        System.out.println("Average Turnaround Time: " + avgTurnAround);
    }
}
