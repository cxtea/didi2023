package com.didi2023.servicemap.remote;

import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.ServiceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceClient {

    @Value("${amap.key}")
    private String ampKey;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult addService(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.SERVICE_ADD_URL)
                .append("?key=")
                .append(ampKey)
                .append("&name=")
                .append(name);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toString(),null,String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String sid = data.getString("sid");

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSid(sid);


        return ResponseResult.success(serviceResponse);


    }
}
