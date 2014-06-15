package ua.kture.ioshchenko.dao;

import ua.kture.ioshchenko.model.Channel;
import ua.kture.ioshchenko.model.ChannelAction;

import java.util.List;

public interface ChannelDAO {
    List<Channel> getAllServiceThis();

    List<Channel> getAllServiceThat();

    List<ChannelAction> getListServiceActionThat(long id);

    List<ChannelAction> getListServiceActionThis(long id);


    Channel getChanel(long id);

    ChannelAction getChanelActionThis(long id);

    ChannelAction getChanelActionThat(long id);

}
