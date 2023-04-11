package com.didi2023.servicemap.remote;

import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.request.AmapDTO;
import com.didi2023.internalcommon.dto.Point;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TraceResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PointClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String sid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<TraceResponse> upload(AmapDTO amap) {
        String tid = amap.getTid();
        Point[] points = amap.getPoints();


        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.TRACE_POINT_UPDATE_URL)
//                .append("?key=")
//                .append(amapKey)
//                .append("&sid=")
//                .append(sid)
//                .append("&")
//                .append("tid=")
//                .append(tid)
//                .append("&points=")
//                .append(points)
                ;
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("key", amapKey);
        map.add("sid", sid);
        map.add("tid", tid);
        map.add("points", points);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toString(), map, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");

        log.info("data:{}", data);

        return ResponseResult.success("");
    }

}
