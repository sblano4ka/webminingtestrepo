package ua.kture.ioshchenko.bean;

public class RecipeBean {
    private long id;
    private long channelThisId;
    private long channelActionThisId;
    private long channelThatId;
    private long channelActionThatId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChannelThisId() {
        return channelThisId;
    }

    public void setChannelThisId(long channelThisId) {
        this.channelThisId = channelThisId;
    }

    public long getChannelActionThisId() {
        return channelActionThisId;
    }

    public void setChannelActionThisId(long channelActionThisId) {
        this.channelActionThisId = channelActionThisId;
    }

    public long getChannelThatId() {
        return channelThatId;
    }

    public void setChannelThatId(long channelThatId) {
        this.channelThatId = channelThatId;
    }

    public long getChannelActionThatId() {
        return channelActionThatId;
    }

    public void setChannelActionThatId(long channelActionThatId) {
        this.channelActionThatId = channelActionThatId;
    }
}
