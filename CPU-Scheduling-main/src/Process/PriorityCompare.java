package Process;

import java.util.Comparator;
public class PriorityCompare implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
        return o1.getPriority()-o2.getPriority();
    }
}