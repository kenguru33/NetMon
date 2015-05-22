package cx.virtlab.netmon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by bernt on 21.05.15.
 */
public class MonitorController implements Initializable {

    public Monitor monitor;


    @FXML
    private ToggleButton toggleModeButton;

    @FXML
    private void handleToggleModeButtonAction(ActionEvent event) {
        if (((ToggleButton)event.getSource()).isSelected()) {
            this.monitor.setContinues(true);
        } else this.monitor.setContinues(false);
    }



    public MonitorController() {
        this.monitor = Monitor.createMonitor();

        this.monitor.setUseNativePing(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
