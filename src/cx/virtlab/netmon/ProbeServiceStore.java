package cx.virtlab.netmon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernt on 26.05.15.
 */
public class ProbeServiceStore {

    private ObservableList<ProbeService> probeServiceList;

    public ObservableList<ProbeService> getProbeServiceList() {
        return probeServiceList;
    }

    private ProbeServiceStore() {
        this.probeServiceList = FXCollections.observableArrayList();
    }

    public static ProbeServiceStore createProbeServiceStore() {
        return new ProbeServiceStore();
    }

    public void load(String filename) {
        dummyData();
    }

    public void save(String filename) {

    }

    public void dummyData() {
        this.probeServiceList.add(ProbeService.createProbeService(PingProbe.createPingProbe("192.168.1.1",1000),1000));
        this.probeServiceList.add(ProbeService.createProbeService(PingProbe.createPingProbe("192.168.1.26",1000),1000));
        this.probeServiceList.add(ProbeService.createProbeService(PingProbe.createPingProbe("192.168.1.111",1000),1000));
    }
}
