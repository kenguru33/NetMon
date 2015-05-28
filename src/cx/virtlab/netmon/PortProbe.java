package cx.virtlab.netmon;

/**
 * Created by bernt on 25.05.15.
 */
public class PortProbe implements Probe {

    private String url;
    private int port;
    private int timeout;

    PortProbe(String url, int port, int timeout) {
        this.url = url;
        this.port = port;
        this.timeout = timeout;
    }
    
    @Override
    public ProbeResponse probe() {
        return null;
    }

    public int getTimeout() {
        return 0;
    }

    public String getUrl() {
        return null;
    }

    public int getPort() {
        return 0;
    }
}
