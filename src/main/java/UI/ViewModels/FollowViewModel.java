package UI.ViewModels;

import java.io.Serializable;

public class FollowViewModel implements Serializable {

    private int followId;
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

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }
}
