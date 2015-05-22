package cx.virtlab.netmon;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by bernt on 21.05.15.
 */
public class MonitorController implements Initializable {

    public Monitor monitor;

    public MonitorController() {
        this.monitor = Monitor.createMonitor();

        this.monitor.setUseNativePing(true);
        this.monitor.setContinues(true);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.monitor.setContinues(false);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.monitor.setContinues(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
