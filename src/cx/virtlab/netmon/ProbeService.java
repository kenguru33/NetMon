package cx.virtlab.netmon;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernt on 25.05.15.
 */

public class ProbeService extends Service<ProbeResponse> {

    List<Probe> probes;

    private Probe probe;

    private int interval;
    private String name = "ProbeServiceName";

    private ProbeService(Probe probe, int interval) {
        this.probe = probe;
        this.interval = interval;
    }

    ProbeService(String name, int interval) {
        this.name = name;
        this.interval = interval;
        this.probes = new ArrayList<>();
    }





    static ProbeService createProbeService(Probe probe, int interval) {
        return new ProbeService(probe, interval);
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

    public void setProbe(Probe probe) {
        this.probe = probe;
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
