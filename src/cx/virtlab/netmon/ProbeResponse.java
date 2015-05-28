package cx.virtlab.netmon;

/**
 * Created by bernt on 24.05.15.
 */
public class ProbeResponse {
    private Boolean isResponding;
    private String responseDescription;

    private ProbeResponse(Boolean isResponding, String responseDescription) {
        this.isResponding = isResponding;
        this.responseDescription = responseDescription;
    }

    public static ProbeResponse createProbeResponse(Boolean isResponding, String responseDescription) {
        return new ProbeResponse(isResponding, responseDescription);
    }

    public Boolean isResponding() {
        return isResponding;
    }

    public String getResponseDescription() {
        return responseDescription;
    }
}
