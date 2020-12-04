package network.Group;

/**
 * An interface that represents all the group data has, such as the group <code>name</code> and it's <code>description</code>
 * @author 57747_57833
 */
public interface GroupData {

    /**
     * Returns the name of this group
     * @return The name of the group as <code>name</code>
     */
    String getName();

    /**
     * Returns the description of this group
     * @return The description of the group as <code>String</code>
     */
    String getDescription();
}