package cx.virtlab.netmon;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;

/**
 * Created by bernt on 25.05.15.
 */

public class ProbeService extends Service<ProbeResponse> {

    private Probe probe;

    private int interval;
    private String name = "ProbeServiceName";

    ProbeService(Probe probe, int interval) {
        this.probe = probe;
        this.interval = interval;
    }

    @Override
    protected Task<ProbeResponse> createTask() {
        return new Task<ProbeResponse>() {
            @Override
            protected ProbeResponse call() throws Exception {
                System.out.println("Probing...");
                Thread.sleep(interval);
                return probe.probe();
            }
        };
    }

    public Probe getProbe() {
        return probe;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
