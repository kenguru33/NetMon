package cx.virtlab.netmon;

/**
 * Created by bernt on 24.05.15.
 */
public class ProbeResponse {
    private Boolean isResponding;
    private String responseDescription;

    ProbeResponse(Boolean isResponding, String responseDescription) {
        this.isResponding = isResponding;
        this.responseDescription = responseDescription;
    }



    public Boolean isResponding() {
        return isResponding;
    }

    public String getResponseDescription() {
        return responseDescription;
    }
}
