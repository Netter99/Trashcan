package org.service.Impl;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;
import org.dao.Impl.IntelligenceTrashcanDaoImpl;
import org.dao.IntelligenceTrashcanDao;
import org.model.CanInfo;
import org.service.CanService;
import org.util.DBUtil;
import org.util.DistanceUtil;
import org.util.GeoHashUtil;
import org.vo.TrashCan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @date 2019/4/23 23:32
 */
public class CanServiceImpl implements CanService {

    public static void main(String[] args) {


        CanService canService = new CanServiceImpl();

        System.out.println("654545");
        List<TrashCan> nearByTrashCan = canService.getNearByTrashCan(108.93772, 34.341044);
        System.out.println("wewq");
        System.out.println("qeewqe" + nearByTrashCan);

        /*System.out.println("执行");
        List<TrashCan> trashCan = canService.getNearByTrashCan("0.0.0.0");
        System.out.println(trashCan.toString());*/
    }

    private IntelligenceTrashcanDao trashcanDao = new IntelligenceTrashcanDaoImpl();

    @Override
    public boolean ipExists(String ip) {
        return trashcanDao.isIpExists(ip);
    }


    @Override
    public CanInfo getCanInfoByIp(String ip) {
        return trashcanDao.getCanByIp(ip);
    }

    @Override
    public List<TrashCan> getNearByTrashCan(String ip) {
        CanInfo canInfo = this.getCanInfoByIp(ip);
        if (canInfo != null) {
            String geoHash = canInfo.getGeoHash();
            WGS84Point wgs84Point = GeoHashUtil.getLocationByGeoHashString(geoHash);
            GeoHash[] adjacents = GeoHashUtil.getAdjacents(wgs84Point.getLongitude(), wgs84Point.getLatitude(), 6);
            String[] geoHashStrs = new String[adjacents.length];
            for (int i = 0; i < adjacents.length; i++) {
                geoHashStrs[i] = adjacents[i].toBase32();
            }
            List<CanInfo> canInfos = trashcanDao.getNearByCanGeoHashStr(geoHashStrs);
            List<TrashCan> trashCans = new ArrayList<>();
            for (CanInfo info : canInfos) {
                TrashCan trashCan = new TrashCan();
                trashCan.setLongitude(info.getLongitude());
                trashCan.setLatitude(info.getLatitude());
                //算出该垃圾桶距离当前垃圾桶的距离
                trashCan.setDistance(DistanceUtil.distance(canInfo.getLongitude(), canInfo.getLatitude(), info.getLongitude(), info.getLatitude()));
                trashCans.add(trashCan);
            }
            return trashCans;

        }
        return null;
    }

    @Override
    public List<TrashCan> getNearByTrashCan(double longitude, double latitude) {
        System.out.println("ggfdgfd");
        GeoHash geoHash = GeoHashUtil.getGeoHash(longitude, latitude, 6);
        String nowLocStr = geoHash.toBase32();
        GeoHash[] adjacents = GeoHashUtil.getAdjacents(longitude, latitude, 6);
        String[] geoHashStrs = new String[adjacents.length + 1];
        geoHashStrs[0] = nowLocStr;
        System.out.println("循环前");
        for (int i = 1; i < geoHashStrs.length; i++) {
            geoHashStrs[i] = adjacents[i - 1].toBase32();
        }
        System.out.println("循环后");
        List<CanInfo> canInfos = trashcanDao.getNearByCanGeoHashStr(geoHashStrs);
        System.out.println("数据库查询");
        List<TrashCan> trashCans = new ArrayList<>();
        int i = 1;
        for (CanInfo info : canInfos) {
            System.out.println("i"+ i++);
            TrashCan trashCan = new TrashCan();
            trashCan.setLongitude(info.getLongitude());
            trashCan.setLatitude(info.getLatitude());
            //算出该垃圾桶距离当前垃圾桶的距离
            trashCan.setDistance(DistanceUtil.distance(longitude, latitude, info.getLongitude(), info.getLatitude()));
            trashCans.add(trashCan);
        }
        return trashCans;
    }

}
