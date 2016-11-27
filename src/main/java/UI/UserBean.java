package UI;

import UI.ViewModels.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.lang.reflect.Type;
import java.util.List;


@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
    private UserViewModel user;
    private RestClient client;
    private boolean isAuthenticated;
    private String searchTerm;

    public UserBean() {
        this.user = new UserViewModel();
        isAuthenticated = false;
        searchTerm = "";
        client = new RestClient();
    }

    public UserViewModel getUser() {
        return this.user;
    }
    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String register(){
        this.user = new UserViewModel(user.getUsername(),user.getPassword());
        client.setPath("/User/Register");
        ClientResponse response = client.post(new Gson().toJson(this.user));
        if (response.getStatus() != 200) {
            return "/register.xhtml";
        }else {
            return "/login.xhtml";
        }
    }
    public String login(){
        client.setPath("/User/Login");
        ClientResponse response = client.post(new Gson().toJson(this.user));
        if (response.getStatus() != 200) {
            return "/login.xhtml";
        }else {
            this.user = new Gson().fromJson(response.getEntity(String.class),UserViewModel.class);
            isAuthenticated = true;
            return "/home.xhtml";
        }
    }
    public List<UserViewModel> getUsers(){
        if(searchTerm.length()<1) {
            client.setPath("/User/Users/" + user.getUserId());
        } else {
            client.setPath("/User/Users/" + user.getUserId()+"/"+searchTerm);
        }
        ClientResponse response = client.get();
        Type type = new TypeToken<List<UserViewModel>>() {}.getType();
        return new Gson().fromJson(response.getEntity(String.class),type);
    }
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml";
    }
}
