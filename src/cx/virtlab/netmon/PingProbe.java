package cx.virtlab.netmon;

import java.io.IOException;

/**
 * Created by bernt on 24.05.15.
 */
public class PingProbe implements Probe {

    private Integer timeout;
    private String url;

    private PingProbe(String url, Integer timeout) {
        this.url = url;
        this.timeout = timeout;
    }

    public static PingProbe createPingProbe(String url, Integer timeout) {
        return new PingProbe(url, timeout);
    }

    @Override
    public ProbeResponse probe() {
        String msg;
        Boolean reachable = false;
        Process p1 = null;
        try {
            p1 = Runtime.getRuntime().exec("ping -c 1 -t " + this.timeout + " " + this.url);
        } catch (IOException e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        int returnVal = 0;
        try {
            returnVal = p1.waitFor();
            reachable = (returnVal==0);
            if (reachable) {
                msg = "Successfully Connected to " + this.url;
            } else {
                msg = "Connection to " + this.url + " Failed!";
            }
            return ProbeResponse.createProbeResponse(reachable, msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return ProbeResponse.createProbeResponse(reachable, msg);
    }

    public int getTimeout() {
        return timeout;
    }

    public String getUrl() {
        return url;
    }
}
