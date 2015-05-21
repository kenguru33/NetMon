package cx.virtlab.netmon;

import javafx.beans.property.*;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by bernt on 21.05.15.
 */
public class Monitor {

    private StringProperty url;
    private BooleanProperty connected;
    private StringProperty statusMessage;
    private IntegerProperty timeout;
    private LongProperty interval;
    private BooleanProperty continues;
    private Thread thread;


    private Monitor() {

        this.url = new SimpleStringProperty("localhost");
        this.statusMessage = new SimpleStringProperty("No status message defined");
        this.timeout = new SimpleIntegerProperty(10);
        this.interval = new SimpleLongProperty(1000);
        this.connected = new SimpleBooleanProperty(false);
        this.continues = new SimpleBooleanProperty(false);

        this.thread = new Thread(){
            public void run(){
                while (true) {
                    updateStatus();
                    try {
                        sleep(getInterval());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }

    public static Monitor createMonitor() {
        return new Monitor();
    }

    public void updateStatus() {
        System.out.println("updating status...");
        try {
            InetAddress address = InetAddress.getByName(this.getUrl());
            this.setConnected(address.isReachable(this.getTimeout()));
        } catch (IOException e) {
            e.printStackTrace();
            this.setStatusMessage(e.getMessage());
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

    public void setInterval(int interval) {
        this.interval.set(interval);
    }

    public boolean getContinues() {
        return continues.get();
    }

    public BooleanProperty continuesProperty() {
        return continues;
    }

    public void setContinues(boolean continues) {
        this.continues.set(continues);
        if (continues) {
            this.thread.start();
            System.out.println("Thread started...");
        } else {
            System.out.println("Stop thread here");
        }
    }
}
