package UI;

import UI.ViewModels.FollowViewModel;
import UI.ViewModels.PostViewModel;
import UI.ViewModels.UserViewModel;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;


@ManagedBean
@SessionScoped
public class PostBean implements Serializable {
    private PostViewModel post;
    private RestClient client;

    @ManagedProperty(value="#{userBean.user}")
    private UserViewModel user;
    public UserViewModel getUser() {
        return user;
    }
    public void setUser(UserViewModel user) {
        this.user = user;
    }

    @ManagedProperty(value="#{followBean.follows}")
    private List<FollowViewModel> follows;
    public List<FollowViewModel> getFollows() {
        return follows;
    }
    public void setFollows(List<FollowViewModel> follows) {
      this.follows=follows;
    }

    public PostBean() {
        this.post = new PostViewModel();
        this.client = new RestClient();
    }

    public PostViewModel getPost() {
        return post;
    }

    public void setPost(PostViewModel post) {
        this.post = post;
    }

    public void createPost(){
        this.post.setUser(this.user);
        client.setPath("/Post/Create");
        ClientResponse response = client.post(new Gson().toJson(this.post));
    }

   public List<PostViewModel> getYourPosts(){
       client.setPath("/Post/YourPosts");
       ClientResponse response = client.post(new Gson().toJson(this.user));
       Type type = new TypeToken<List<PostViewModel>>() {}.getType();
       return new Gson().fromJson(response.getEntity(String.class),type);
    }
    public List<PostViewModel> getFollowingPosts(){
        client.setPath("/Post/FollowingPosts");
        Type type = new TypeToken<List<FollowViewModel>>() {}.getType();
        ClientResponse response = client.post(new Gson().toJson(this.follows,type));
        type = new TypeToken<List<PostViewModel>>() {}.getType();
        return new Gson().fromJson(response.getEntity(String.class),type);
    }
    public String getCharsLeft(){
        int charsLeft = 255 - post.getBody().length();
        return "characters left: " +
                (charsLeft > 0 ? charsLeft : 0);
    }
}
