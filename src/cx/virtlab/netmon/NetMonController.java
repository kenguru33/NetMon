package cx.virtlab.netmon;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bernt on 20.05.15.
 */
public class NetMonController implements Initializable{


    public Button btnSettings;
    public Button btnSelfDiagnose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init javafx");
    }

    public void setBtnSettingsClicked() {
        System.out.println("Openening settings....");
    }
}
