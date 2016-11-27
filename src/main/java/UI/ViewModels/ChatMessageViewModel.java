package UI.ViewModels;

import java.io.Serializable;
import java.sql.Date;

public class ChatMessageViewModel implements Serializable{
    private int chatMessageId;
    private String message;
    private Date sendDate;
    private UserViewModel sender;
    private UserViewModel receiver;

    public ChatMessageViewModel() {
    }

    public int getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(int chatMessageId) {
        this.chatMessageId = chatMessageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public UserViewModel getSender() {
        return sender;
    }

    public void setSender(UserViewModel sender) {
        this.sender = sender;
    }

    public UserViewModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserViewModel receiver) {
        this.receiver = receiver;
    }

}
