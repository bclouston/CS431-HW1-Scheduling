import java.util.LinkedList;
import java.util.Comparator;

public class ShortestJobFirst {

    public ShortestJobFirst(int[][] pList) {
        LinkedList<int[]> ready = new LinkedList<>();
        int time = 0;
        int remaining = pList.length;
        while (remaining > 0) {
            // wait for process to arrive if ready list is empty
            if (ready.isEmpty()) {
                for (int[] process : pList) {
                    if (process[0] == time) {
                        ready.add(process);
                    }
                }
                // increment time if no process added to ready list
                if (ready.isEmpty()) {
                    time++;
                }
            }
            if (!ready.isEmpty()) {
                // run process at front of ready list until it completes
                for (int i = 0; i < ready.element()[1]; i++) {
                    time++;
                    // increment waiting time for any inactive processes in ready list
                    for (int[] process : ready) {
                        if (process[3] != ready.element()[3]) {
                            process[2]++;
                        }
                    }
                    // check if new process arrived, move into ready list
                    for (int[] process : pList) {
                        if (process[0] == time) {
                            ready.add(process);
                        }
                    }
                }
                // remove completed process from ready list and update pList
                for (int i = 0; i < pList.length; i++) {
                    if (pList[i][3] == ready.element()[3]) {
                        pList[i] = ready.poll();
                        remaining--;
                        break;
                    }
                }
                // sort ready list according to cpu time
                ready.sort(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] p1, int[] p2) {
                        return Integer.compare(p1[1], p2[1]);
                    }
                });
            }
        }
        // print results and calculate averages
        System.out.println("+----+--------------+----------+--------------+-----------------+");
        System.out.printf("| %-1s | %-12s | %-8s | %-12s | %-15s |",
                "ID", "Arrival Time", "CPU Time", "Waiting Time", "Turnaround Time");
        System.out.println("\n+----+--------------+----------+--------------+-----------------+");
        int waitTotal = 0, tatTotal = 0;
        for (int[] process : pList) {
            waitTotal += process[2];
            tatTotal += process[1] + process[2];
            System.out.printf("| %-2s | %-12s | %-8s | %-12s | %-15s |\n",
                    process[3], process[0], process[1], process[2], process[1] + process[2]);
        }
        System.out.println("+----+--------------+----------+--------------+-----------------+");
        System.out.println("Average Waiting Time: " + (waitTotal / pList.length));
        System.out.println("Average Turnaround Time: " + (tatTotal / pList.length));
    }
}
