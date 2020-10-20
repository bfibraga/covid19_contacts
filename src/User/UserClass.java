package User;

public class UserClass implements User {
    private String login;
    private String name;
    private int age;
    private String local;
    private String profession;

    public UserClass(String login, String name, int age, String local, String profession){
        this.login = login;
        this.name = name;
        this.age = age;
        this.local = local;
        this.profession = profession;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getAddress() {
        return local;
    }

    @Override
    public String getProfession() {
        return null;
    }
}
