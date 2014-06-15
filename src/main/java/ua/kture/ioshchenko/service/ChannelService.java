package ua.kture.ioshchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kture.ioshchenko.dao.ChannelDAO;
import ua.kture.ioshchenko.model.Channel;
import ua.kture.ioshchenko.model.ChannelAction;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelDAO channelDAO;

    public List<Channel> getAllServiceThis() {
        return channelDAO.getAllServiceThis();
    }

    public List<Channel> getAllServiceThat() {
        return channelDAO.getAllServiceThat();
    }

    public List<ChannelAction> getListServiceActionThat(long id) {
        return channelDAO.getListServiceActionThat(id);
    }

    public List<ChannelAction> getListServiceActionThis(long id) {
        return channelDAO.getListServiceActionThis(id);
    }
}
