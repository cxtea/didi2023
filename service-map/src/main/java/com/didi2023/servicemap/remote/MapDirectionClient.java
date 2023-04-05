package com.didi2023.servicemap.remote;

import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

;

@Service
@Slf4j
public class MapDirectionClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey;

    public DirectionResponse direction(String origin, String destination) {


        // https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=xml&key=<用户的key>
        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.DIRECTION_URL);
        builder.append("origin=" + origin);
        builder.append("&destination=" + destination);
        builder.append("&extensions=all");
        builder.append("&output=json");
        builder.append("&key=" + amapKey);


        log.info(builder.toString());
        //调用高德地图API

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.toString(), String.class);
        String responseString = responseEntity.getBody();

        //解析返回结果
        DirectionResponse directionResponse = parseDirectionResponse(responseString);

        log.info(directionResponse.toString());
        return directionResponse;

}
    private DirectionResponse parseDirectionResponse(String responseString) {
        DirectionResponse directionResponse = new DirectionResponse();
        JSONObject jsonObject = JSONObject.fromObject(responseString);
        try {
            if (jsonObject.has(AmapConfigConstants.STATUS) && jsonObject.getInt(AmapConfigConstants.STATUS) == 1) {
                JSONObject route = (JSONObject) jsonObject.get(AmapConfigConstants.ROUTE);
                JSONArray paths = route.getJSONArray(AmapConfigConstants.PATHS);
                JSONObject path = (JSONObject) paths.get(0);
                if (path.has(AmapConfigConstants.DISTANCE)) {
                    int distance = path.getInt(AmapConfigConstants.DISTANCE);
                    directionResponse.setDistance(distance);
                }
                if (path.has(AmapConfigConstants.DURATION)) {
                    int duration = path.getInt(AmapConfigConstants.DURATION);
                    directionResponse.setDuration(duration);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return directionResponse;
    }

}
