package com.mxg.common.utils;

import java.util.HashMap;
import java.util.Map;
 
public class MapDistance { 
       
    private static double EARTH_RADIUS = 6378.137; 
   
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
     
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1 用户经度
     * @param lng1 用户纬度
     * @param lat2 商家经度
     * @param lng2 商家纬度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
         
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance =  Math.round(distance * 10000);
        distance = distance / 10000;
        String distanceStr = distance+"";
       // distanceStr = distanceStr.substring(0, distanceStr.indexOf("."));
         
        
        return distanceStr;
    }
    
    
    /** 
     * 角度弧度计算公式 rad:(). <br/> 
     *  
     * 360度=2π π=Math.PI 
     *  
     * x度 = x*π/360 弧度 
     *  
     * @author chiwei 
     * @param d 
     * @return 
     * @since JDK 1.6 
     */  
    private static double getRadian(double degree) {  
        return degree * Math.PI / 180.0;  
    }  
    
    /** 
     * 根据经纬度计算两点之间的距离 GetDistance:(). <br/> 
     *  
     *  
     * @author chiwei 
     * @param lat1 
     *            1点的纬度 
     * @param lng1 
     *            1点的经度 
     * @param lat2 
     *            2点的纬度 
     * @param lng2 
     *            2点的经度 
     * @return 距离 单位 米 
     * @since JDK 1.6 
     */  
    public static double distance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {  
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);
        double radLat1 = getRadian(lat1);  
        double radLat2 = getRadian(lat2);  
        double a = radLat1 - radLat2;// 两点纬度差  
        double b = getRadian(lng1) - getRadian(lng2);// 两点的经度差  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)  
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        return s * 1000;  
    } 
    
    
     
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
         
        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
 
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
         
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLng = longitude - radiusLng;
        // 获取最大经度
        Double maxLng = longitude + radiusLng;
         
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLat = latitude - radiusLat;
        // 获取最大纬度
        Double maxLat = latitude + radiusLat;
         
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
        
        return map;
    }
     
    public static void main(String[] args) {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123
        //System.out.println(getDistance("117.11811","36.68484","117.00999000000002","36.66123"));
         
        //System.out.println(getAround("117.11811", "36.68484", "13000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
//         System.out.println(getDistance("118.814623","32.063725","118.814000","32.063725"));
//         System.out.println(getDistance(118.81399942436329,32.062431213705246,118.8152465756367,32.06501878629475));
//         System.out.println(getAround("118.814623", "32.063725", "69.4"));
    }
     
}