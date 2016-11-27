package UI;

import UI.ViewModels.ChatMessageViewModel;
import UI.ViewModels.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.lang.reflect.Type;
import java.util.List;

@ManagedBean
@SessionScoped
public class ChatBean {
    private ChatMessageViewModel chat;
    private RestClient client;


    public ChatBean() {
        this.chat = new ChatMessageViewModel();
        client = new RestClient();
    }
    public void setReceiver(UserViewModel receiver){
        this.chat.setReceiver(receiver);
    }
    public ChatMessageViewModel getChat() {
        return chat;
    }

    public void setChat(ChatMessageViewModel chat) {
        this.chat = chat;
    }

    public void sendMessage(UserViewModel sender){
        this.chat.setSender(sender);
        this.client.setPath("/Chat/SendMessage");
        ClientResponse response = client.post(new Gson().toJson(this.chat));
        this.chat.setMessage("");
    }

    public List<ChatMessageViewModel> getChatMessages(UserViewModel sender){
        this.chat.setSender(sender);
        if(chat.getReceiver() != null) {
            client.setPath("/Chat/ChatMessages");
            ClientResponse response = client.post(new Gson().toJson(this.chat));
            Type type = new TypeToken<List<ChatMessageViewModel>>() {}.getType();
            return new Gson().fromJson(response.getEntity(String.class), type);
        } else {
            return null;
        }
    }
}
