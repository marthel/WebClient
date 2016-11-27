package UI.ViewModels;

import java.io.Serializable;

public class FollowViewModel implements Serializable {

    private UserViewModel follower;
    private UserViewModel following;

    public UserViewModel getFollower() {
        return follower;
    }

    public void setFollower(UserViewModel follower) {
        this.follower = follower;
    }

    public UserViewModel getFollowing() {
        return following;
    }

    public void setFollowing(UserViewModel following) {
        this.following = following;
    }
}
