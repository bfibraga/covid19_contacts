package User;

/**
 * An interface
 */
public interface UserData extends User{

    /**
     *
     * @return String of the current <code>User</code> to login into the program
     */
    String getLogin();

    /**
     *
     * @return The Name of the current <code>User</>
     */
    String getName();

    /**
     *
     * @return Age of the current <code>User</code>
     */
    int getAge();

    /**
     *
     * @return Address of the current <code>User</code>
     */
    String getAddress();

    /**
     *
     * @return Profession of the current <code>User</code>
     */
    String getProfession();
}
