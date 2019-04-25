package org.dao.Impl;

import org.dao.IntelligenceTrashcanDao;
import org.model.CanInfo;
import org.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @date 2019/4/22 22:39
 */
public class IntelligenceTrashcanDaoImpl implements IntelligenceTrashcanDao {
    @Override
    public void addTrashcan(String ip, double longitude, double latitude, String geoHash) {
        String sql = "insert into can_info(ip,longitude,latitude,geohash) values (?,?,?,?)";
        Object[] params = {ip,longitude,latitude,geoHash};
        DBUtil.executeUpdate(sql,params);
    }

    @Override
    public boolean isIpExists(String ip) {
        String sql = "select count(*) rec from can_info where ip = ?";
        Object[] params = {ip};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if(resultSet != null){
            try {
                resultSet.next();
                int rec = resultSet.getInt("rec");
                return rec != 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public List<CanInfo> getNearByCanGeoHashStr(String[] geoHashStr) {
        String sql = "select id,ip,longitude,latitude,geohash from can_info where geohash like ?";
        List<CanInfo> canInfos = new ArrayList<>();
        for (String s : geoHashStr) {
            s += "%";
            Object[] params = {s};
            ResultSet resultSet = DBUtil.executeQuery(sql, params);
            if(resultSet != null){
                while(true){
                    try {
                        if (resultSet.next()){
                            int id = resultSet.getInt("id");
                            double longitude = resultSet.getDouble("longitude");
                            double latitude = resultSet.getDouble("latitude");
                            String ip = resultSet.getString("ip");
                            String geohash = resultSet.getString("geohash");
                            CanInfo canInfo = new CanInfo();
                            canInfo.setLongitude(longitude);
                            canInfo.setLatitude(latitude);
                            canInfo.setGeoHash(geohash);
                            canInfo.setIp(ip);
                            canInfo.setId(id);
                            System.out.println(canInfo);
                            canInfos.add(canInfo);
                        }
                        break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }else{
                return null;
            }
        }
        return canInfos;
    }

    @Override
    public CanInfo getCanByIp(String ip) {
        String sql = "SELECT id,longitude,latitude,geohash from can_info where ip = ?";
        Object[] params = {ip};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if(resultSet != null){
            try {
                resultSet.next();
                int id = resultSet.getInt("id");
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");
                String geohash = resultSet.getString("geohash");
                CanInfo canInfo = new CanInfo();
                canInfo.setLongitude(longitude);
                canInfo.setLatitude(latitude);
                canInfo.setGeoHash(geohash);
                canInfo.setIp(ip);
                canInfo.setId(id);
                return canInfo;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
