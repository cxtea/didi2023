package com.didi2023.servicemap.remote;

import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapDicDistrictClient {

    @Value("${amap.key}")
    private String key ;
    @Autowired
    private RestTemplate restTemplate;
    public String initDistrict(String keywords){
        //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>

        StringBuilder builder = new StringBuilder(AmapConfigConstants.DISTRICT_URL);
        builder.append("?");
        builder.append("keywords="+keywords);
        builder.append("&");
        builder.append("subdistrict="+3);
        builder.append("&");
        builder.append("key="+key);

        String url = builder.toString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String responseString = entity.getBody();

        return responseString;
    }

}
