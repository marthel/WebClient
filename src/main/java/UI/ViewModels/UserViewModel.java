package UI.ViewModels;


import java.io.Serializable;

public class UserViewModel implements Serializable {
    private int userId;
    private String username;
    private String password;
    public UserViewModel() {
    }
    public UserViewModel(String username, String password) {
        this.username = username;
        this.password=password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
