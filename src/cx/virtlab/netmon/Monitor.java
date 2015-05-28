package cx.virtlab.netmon;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by bernt on 24.05.15.
 */
public class Monitor implements Runnable {
    SimpleBooleanProperty isResponding = new SimpleBooleanProperty(false);
    SimpleStringProperty responseDescription = new SimpleStringProperty();
    SimpleIntegerProperty interval = new SimpleIntegerProperty(0);
    SimpleBooleanProperty continuesMode = new SimpleBooleanProperty(true);

    Probe probe;
    Thread probeThread;

    private Monitor(Probe probe, int interval) {
        this.probe = probe;
        this.setInterval(interval);
    }

    public static Monitor createNetMonitor(Probe probe, int interval) {
        return new Monitor(probe, interval);
    }

    public long getInterval() {
        return interval.get();
    }

    public SimpleIntegerProperty intervalProperty() {
        return interval;
    }

    public void setInterval(int interval) {
        // If a thread already is running stop it.¨
        // Fix this with a named thread group.
        if (this.interval.getValue() > 0) {
            this.probeThread.interrupt();
        }
        this.interval.set(interval);
        // Create new thread if intervall is grater that 0
        if (interval > 0 && continuesMode.get()) {
            this.probeThread = new Thread(this);
            System.out.println("Starting thread");
            this.probeThread.start();
        }

    }

    public void setContinuesMode(boolean continuesMode) {
        this.continuesMode.set(continuesMode);
    }

    public boolean getContinuesMode() {
        return continuesMode.get();
    }

    public SimpleBooleanProperty continuesModeProperty() {
        return continuesMode;
    }

    @Override
    public void run() {
        System.out.println("Running in Continues mode.");
        while (!this.probeThread.isInterrupted()) {
            ProbeResponse response = this.probe.probe();
            this.responseDescription.set(response.getResponseDescription());
            this.isResponding.set(response.isResponding());
            try {
                Thread.sleep(getInterval());
            } catch (InterruptedException e) {
                this.probeThread.interrupt();
            }
        }
        this.probeThread.interrupt();
        System.out.println("Runnning in Manual mode.");
    }
}
