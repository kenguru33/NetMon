package cx.virtlab.netmon;

import javafx.scene.control.TreeItem;

/**
 * Created by bernt on 28.05.15.
 */
public class ProbeServiceGroupTreeItem extends TreeItem<String> {

    ProbeServiceGroupTreeItem() {

    }
    @Override
    public boolean isLeaf() {
        return false;
    }
}
