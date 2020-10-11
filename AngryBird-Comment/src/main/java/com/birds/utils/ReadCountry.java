package com.birds.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.InetAddress;

@Component
public class ReadCountry {

    private String Country_PATH = "GeoLite2-Country.mmdb" ;
//    private File database = new File(Country_PATH);
    private File database = null;
    private DatabaseReader reader = null;

    public ReadCountry(){
        try{
            database = ResourceUtils.getFile("classpath:GeoLite2-Country.mmdb");
            reader = new DatabaseReader.Builder(database).build();
        }catch (Exception e){
            //异常处理
        }
    }
    /**
     * 根据IP获取国家二字码
     * @param ip ip
     * @return 国家二字码
     */
    public String getCoutryCode(String ip){
        String result = null;

        try{
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = reader.country(ipAddress);
            Country country = response.getCountry();
            result = country.getIsoCode();
        } catch(Exception e1) {
            //异常处理
            e1.printStackTrace();
        }
        return result;
    }
}
