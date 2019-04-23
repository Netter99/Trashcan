package org.util;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

/**
 * @author zsw
 * @date 2019/4/22 21:00
 */
public class GeoHashUtil {

    public static void main(String[] args) {
        GeoHash geoHash = getGeoHash(108.937720, 34.341044, 12);
        System.out.println("geohash值为" + geoHash.toBase32());
        WGS84Point location = getLocationByGeoHashString(geoHash.toBase32());
        System.out.println("经度为：" + location.getLongitude() +" 纬度为：" +location.getLatitude());

        GeoHash[] adjacents = getAdjacents(108.937720, 34.341044, 6);
        int i = 1;
        for (GeoHash adjacent : adjacents) {
            System.out.println("距离为" + DistanceUtil.distance(geoHash.getPoint().getLongitude(), geoHash.getPoint().getLatitude(), adjacent.getPoint().getLongitude(), adjacent.getPoint().getLatitude()));
            System.out.println("第" + i + "个:" + adjacent.toBase32() );
            System.out.println("第" + i + "个的经度为:" + adjacent.getPoint().getLongitude()  + " 纬度为："  + adjacent.getPoint().getLatitude());
            i++;
        }

    }

    /**
     * 将经纬度转为GeoHash值
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    public static GeoHash getGeoHash(double longitude, double latitude, int numberOfCharacters) {
        return GeoHash.withCharacterPrecision(latitude, longitude, numberOfCharacters);
    }

    /**
     * 获取周围的区域的geohash
     *
     * @param longitude          经度
     * @param latitude           纬度
     * @param numberOfCharacters Characters数
     * @return
     */
    public static GeoHash[] getAdjacents(double longitude, double latitude, int numberOfCharacters) {
        GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, numberOfCharacters);
        return geoHash.getAdjacent();
    }

    public static WGS84Point getLocationByGeoHashString(String geoHashStr){
        GeoHash geoHash = GeoHash.fromGeohashString(geoHashStr);
        return geoHash.getPoint();
    }
}
