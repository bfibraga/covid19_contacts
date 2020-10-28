package network.User;

/**
 * An interface
 */
public interface UserData {

    /**
     *
     * @return String of the current <code>network.User</code> to login into the program
     */
    String getLogin();

    /**
     *
     * @return The Name of the current <code>network.User</>
     */
    String getName();

    /**
     *
     * @return Age of the current <code>network.User</code>
     */
    int getAge();

    /**
     *
     * @return Address of the current <code>network.User</code>
     */
    String getAddress();

    /**
     *
     * @return Profession of the current <code>network.User</code>
     */
    String getProfession();
}
