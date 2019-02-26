package sample.entities;

/**
 * Класс пользователя, на основе которого создается пользователь при регистрации  в системе.
 * <p>
 * Date: 24.01.2019 (четверг)
 * Project name: TestApplication
 * Package name: sample
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class User {

    private String login;
    private String password;
    private String role;
    private String name;

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public User(String login, String password, String role, String name) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
