package org.model;

import lombok.Data;

/**
 * @author zsw
 * @date 2019/4/22 22:30
 */
@Data
public class CanInfo {
    private Integer id;
    private String ip;
    private double longitude;
    private double latitude;
    private String geoHash;

}
