package cx.virtlab.netmon;

import javafx.beans.property.*;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by bernt on 21.05.15.
 */
public class Monitor implements Runnable {

    private StringProperty url;
    private BooleanProperty connected;
    private StringProperty statusMessage;
    private IntegerProperty timeout;
    private LongProperty interval;
    private BooleanProperty continues;
    private BooleanProperty useNativePing;
    private Thread thread;


    private Monitor() {

        this.url = new SimpleStringProperty("10.1.1.254");
        this.statusMessage = new SimpleStringProperty("No status message defined");
        this.timeout = new SimpleIntegerProperty(1000);
        this.interval = new SimpleLongProperty(1000);
        this.connected = new SimpleBooleanProperty(false);
        this.continues = new SimpleBooleanProperty(false);
        this.useNativePing = new SimpleBooleanProperty(false);
        //this.thread = new Thread(this);
    }

    public static Monitor createMonitor() {
        return new Monitor();
    }

    public void updateStatus() {

        System.out.print("Is connected: ");
        if (this.getUseNativePing()) {
            Process p1 = null;
            try {
                p1 = Runtime.getRuntime().exec("ping -c " + this.getInterval()/1000 + " " + this.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int returnVal = 0;
            try {
                returnVal = p1.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean reachable = (returnVal==0);
            this.setConnected(reachable);
            System.out.println(this.getConnected());

        } else {
            try {
                InetAddress address = InetAddress.getByName(this.getUrl());
                this.setConnected(address.isReachable(this.getTimeout()));
                System.out.println(this.getConnected());
            } catch (IOException e) {
                e.printStackTrace();
                this.setStatusMessage(e.getMessage());
            }
        }
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getStatusMessage() {
        return statusMessage.get();
    }

    public StringProperty statusMessageProperty() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage.set(statusMessage);
    }

    public boolean getConnected() {
        return connected.get();
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected.set(connected);
    }

    public int getTimeout() {
        return timeout.get();
    }

    public IntegerProperty timeoutProperty() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout.set(timeout);
    }

    public long getInterval() {
        return interval.get();
    }

    public LongProperty intervalProperty() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval.set(interval);
    }

    public boolean getContinues() {
        return continues.get();
    }

    public BooleanProperty continuesProperty() {
        return continues;
    }

    public void setContinues(boolean continues) {
        // check if state is changing
        if (this.getContinues() != continues) {
            this.continues.set(continues);
            // if state is changing to continues
            if (continues) {
                System.out.println("Continues mode starting...");
                this.thread = new Thread(this);
                this.thread.start();
            }
            // else, state is non-continous and emit interrup to stop thread.
            else {
                this.thread.interrupt();
                System.out.println("Exiting Continues mode...");
            }
        }
    }

    public boolean getUseNativePing() {
        return useNativePing.get();
    }

    public BooleanProperty useNativePingProperty() {
        return useNativePing;
    }

    public void setUseNativePing(boolean useNativePing) {
        this.useNativePing.set(useNativePing);
    }

    @Override
    public void run() {
        while (!this.thread.isInterrupted()) {
            //System.out.println("Running in Continues mode.");
            this.updateStatus();
            try {
                // lag sjekk for at intervall ikke er kortere en ping timeout.
                Thread.sleep(getInterval());
            } catch (InterruptedException e) {
                this.thread.interrupt();
            }
        }
        this.thread.interrupt();
        //System.out.println("Runnning manual mode.");
    }
}
