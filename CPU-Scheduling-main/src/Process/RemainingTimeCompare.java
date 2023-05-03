package Process;

import java.util.Comparator;
public class RemainingTimeCompare implements Comparator<Process> {


    @Override
    public int compare(Process o1, Process o2) {
        return o1.getRemainingTime()-o2.getRemainingTime();
    }
}

