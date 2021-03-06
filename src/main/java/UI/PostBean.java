package UI;

import UI.ViewModels.FollowViewModel;
import UI.ViewModels.PostViewModel;
import UI.ViewModels.UserViewModel;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;


@ManagedBean
@SessionScoped
public class PostBean implements Serializable {
    private PostViewModel post;
    private RestClient client;

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

    public void createPost(UserViewModel user){
        this.post.setUser(user);
        client.setPath("/Post/Create");
        ClientResponse response = client.post(new Gson().toJson(this.post));
    }

   public List<PostViewModel> getYourPosts(UserViewModel user){
       client.setPath("/Post/YourPosts");
       ClientResponse response = client.post(new Gson().toJson(user));
       Type type = new TypeToken<List<PostViewModel>>() {}.getType();
       return new Gson().fromJson(response.getEntity(String.class),type);
    }
    public List<PostViewModel> getFollowingPosts(List<FollowViewModel> follows){
        client.setPath("/Post/FollowingPosts");
        Type type = new TypeToken<List<FollowViewModel>>() {}.getType();
        ClientResponse response = client.post(new Gson().toJson(follows,type));
        type = new TypeToken<List<PostViewModel>>() {}.getType();
        return new Gson().fromJson(response.getEntity(String.class),type);
    }
    public String getCharsLeft(){
        int charsLeft = 255 - post.getBody().length();
        return "characters left: " +
                (charsLeft > 0 ? charsLeft : 0);
    }
}
