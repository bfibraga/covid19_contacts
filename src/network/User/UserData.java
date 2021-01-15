package network.User;

/**
 * An interface that contains all the <code>User</code> data has.
 * @author 57747_57833
 */
public interface UserData {

    /**
     * Returns the login as a <code>String</code>.
     * @return String of the current <code>network.User</code> to login into the program
     */
    String getLogin();

    /**
     * Retuns it's user <code>name</code> as <code>String</code>
     * @return The Name of the current <code>network.User</>
     */
    String getName();

    /**
     * Returns the <code>age</code> of this user as a <code>Integer</code>
     * @return Age of the current <code>network.User</code>
     */
    int getAge();

    /**
     * Returns the address of this user as <code>String</code>
     * @return Address of the current <code>network.User</code>
     */
    String getAddress();

    /**
     * Returns the profession of this user as <code>String</code>
     * @return Profession of the current <code>network.User</code>
     */
    String getProfession();
}