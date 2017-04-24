package nc.vo.af.assembly_details;

import java.io.Serializable;

public class DeviceItem implements Serializable {
    private boolean isPrior4Spec = false;
    private int order;

    public boolean isPrior4Spec() {
        return isPrior4Spec;
    }

    public void setPrior4Spec(boolean isPrior4Spec) {
        this.isPrior4Spec = isPrior4Spec;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


}
