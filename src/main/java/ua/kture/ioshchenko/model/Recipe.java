package ua.kture.ioshchenko.model;

public class Recipe {
    private long id;
    private Channel channelThis;
    private Channel channelThat;

    private ChannelAction channelActionThis;
    private ChannelAction channelActionThat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Channel getChannelThis() {
        return channelThis;
    }

    public void setChannelThis(Channel channelThis) {
        this.channelThis = channelThis;
    }

    public Channel getChannelThat() {
        return channelThat;
    }

    public void setChannelThat(Channel channelThat) {
        this.channelThat = channelThat;
    }

    public ChannelAction getChannelActionThis() {
        return channelActionThis;
    }

    public void setChannelActionThis(ChannelAction channelActionThis) {
        this.channelActionThis = channelActionThis;
    }

    public ChannelAction getChannelActionThat() {
        return channelActionThat;
    }

    public void setChannelActionThat(ChannelAction channelActionThat) {
        this.channelActionThat = channelActionThat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (id != recipe.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
