package cx.virtlab.netmon;

/**
 * Created by bernt on 24.05.15.
 */
public interface Probe {
    ProbeResponse probe();
    String getUrl();
    int getTimeout();
}
