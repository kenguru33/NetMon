package cx.virtlab.netmon;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bernt on 21.05.15.
 */
public class MonitorController implements Initializable {

    public Monitor monitor;

    public MonitorController() {
        this.monitor = Monitor.createMonitor();
        this.monitor.setContinues(true);
        this.monitor.setContinues(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
