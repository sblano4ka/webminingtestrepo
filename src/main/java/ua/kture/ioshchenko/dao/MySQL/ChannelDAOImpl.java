package ua.kture.ioshchenko.dao.MySQL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.dao.ChannelDAO;
import ua.kture.ioshchenko.dao.ConnectionUtil;
import ua.kture.ioshchenko.dao.DBManager;
import ua.kture.ioshchenko.model.Channel;
import ua.kture.ioshchenko.model.ChannelAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChannelDAOImpl implements ChannelDAO {

    private final Logger logger = Logger.getLogger(ChannelDAOImpl.class);

    @Autowired
    private DBManager manager;

    private final String SELECT_ALL_SERVICE_THIS = "SELECT DISTINCT services.id, services.name  " +
            " FROM services, actions_this " +
            " WHERE services.id=actions_this.service_id; ";

    private final String SELECT_ALL_SERVICE_THAT = " SELECT DISTINCT services.id, services.name  " +
            " FROM services, actions_that " +
            " WHERE services.id=actions_that.service_id; ";

    private final String SELECT_SERVICE_ACTION_BY_ID_SERVICE_THAT = " SELECT DISTINCT actions_that.id, " +
            " actions_that.name,  actions_that.description" +
            " FROM services, actions_that " +
            " WHERE actions_that.service_id=?; ";
    private final String SELECT_SERVICE_ACTION_BY_ID_SERVICE_THIS = " SELECT DISTINCT actions_this.id, " +
            " actions_this.name,  actions_this.description " +
            " FROM services, actions_this " +
            " WHERE actions_this.service_id=?; ";


    @Override
    public List<Channel> getAllServiceThis() {
        List<Channel> list = new ArrayList<Channel>();

        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = manager.getConnection();
            pstmt = connection.prepareStatement(SELECT_ALL_SERVICE_THIS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(extractServices(rs));
            }

        } catch (Exception e) {
            logger.error("Get all service this", e);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);
        }

        return list;
    }


    @Override
    public List<Channel> getAllServiceThat() {
        List<Channel> list = new ArrayList<Channel>();

        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = manager.getConnection();
            pstmt = connection.prepareStatement(SELECT_ALL_SERVICE_THAT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(extractServices(rs));
            }

        } catch (Exception e) {
            logger.error("Get all service that", e);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);
        }

        return list;
    }

    @Override
    public List<ChannelAction> getListServiceActionThat(long id) {
        List<ChannelAction> list = new ArrayList<ChannelAction>();

        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = manager.getConnection();
            pstmt = connection.prepareStatement(SELECT_SERVICE_ACTION_BY_ID_SERVICE_THAT);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(extractServiceActions(rs));
            }

        } catch (Exception e) {
            logger.error("Get all service action that", e);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);
        }

        return list;
    }

    @Override
    public List<ChannelAction> getListServiceActionThis(long id) {
        List<ChannelAction> list = new ArrayList<ChannelAction>();

        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = manager.getConnection();
            pstmt = connection.prepareStatement(SELECT_SERVICE_ACTION_BY_ID_SERVICE_THIS);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(extractServiceActions(rs));
            }

        } catch (Exception e) {
            logger.error("Get all service action this", e);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);
        }

        return list;
    }


    private Channel extractServices(ResultSet rs) throws SQLException {
        Channel service = new Channel();
        service.setId(rs.getLong("services.id"));
        service.setName(rs.getString("services.name"));
        return service;
    }

    private ChannelAction extractServiceActions(ResultSet rs) throws SQLException {
        ChannelAction serviceAction = new ChannelAction();
        serviceAction.setId(rs.getLong("id"));
        serviceAction.setName(rs.getString("name"));
        serviceAction.setDescription(rs.getString("description"));
        return serviceAction;
    }
}
