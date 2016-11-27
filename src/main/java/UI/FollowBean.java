package UI;

import UI.ViewModels.FollowViewModel;
import UI.ViewModels.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;


@ManagedBean
@SessionScoped
public class FollowBean implements Serializable{


    private FollowViewModel follow;
    private RestClient client;
    private String searchTerm;
    @ManagedProperty(value="#{userBean.user}")
    private UserViewModel user;

    public UserViewModel getUser() {
        return user;
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

    public FollowBean() {
        this.follow = new FollowViewModel();
        searchTerm = "";
        client = new RestClient();
    }
    public FollowViewModel getFollow() {
        return follow;
    }

    public void setFollow(FollowViewModel fvw) {
        this.follow = fvw;
    }

    public void addFollow(UserViewModel user){
        this.follow.setFollower(this.user);
        this.follow.setFollowing(user);
        client.setPath("/Follow/Add");
        ClientResponse response = client.post(new Gson().toJson(this.follow));
    }

    public List<FollowViewModel> getFollows(){
        if(searchTerm.length()<1) {
            client.setPath("/Follow/Following/" + user.getUserId());
        } else {
            client.setPath("/Follow/Following/" + user.getUserId()+"/"+searchTerm);
        }
        System.out.println(client.getURL() + client.getPath());
        ClientResponse response = client.get();
        Type type = new TypeToken<List<FollowViewModel>>() {}.getType();
        return new Gson().fromJson(response.getEntity(String.class),type);
    }


}
