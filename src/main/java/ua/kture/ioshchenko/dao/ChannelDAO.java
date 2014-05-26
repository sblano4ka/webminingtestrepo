package ua.kture.ioshchenko.dao;

import ua.kture.ioshchenko.model.Channel;
import ua.kture.ioshchenko.model.ChannelAction;

import java.util.List;

public interface ChannelDAO {
    public List<Channel> getAllServiceThis();

    public List<Channel> getAllServiceThat();

    public List<ChannelAction> getListServiceActionThat(long id);

    public List<ChannelAction> getListServiceActionThis(long id);
}
