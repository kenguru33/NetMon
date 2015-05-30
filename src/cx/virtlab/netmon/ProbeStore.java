package cx.virtlab.netmon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernt on 26.05.15.
 */
public class ProbeStore {
    private ObservableList<Probe> probes;

    private ProbeStore() {
        this.probes = FXCollections.observableArrayList();
    }

    static ProbeStore createProbeStore() {
        return new ProbeStore();
    }

    private void dummyData() {

    }

    public void load(String filename) {
        this.dummyData();
    }

    public void save(String filename) {

    }

    public ObservableList<Probe> getProbes() {
        return probes;
    }
}
