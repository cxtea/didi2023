package com.didi2023.servicemap.remote;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.dto.Point;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.AmapDTO;
import com.didi2023.internalcommon.response.TraceResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

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
        String trid = amap.getTrid();
        Point[] points = amap.getPoints();


        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.TRACE_POINT_UPDATE_URL)
                .append("?key=")
                .append(amapKey)
                .append("&sid=")
                .append(sid)
                .append("&")
                .append("tid=")
                .append(tid)
                .append("&")
                .append("trid=")
                .append(trid)
                .append("&points=");
        String jsonStr = JSONUtil.toJsonStr(points);
        String query = URLUtil.encode(jsonStr);
        builder.append(query);
        log.info("points:{}",query);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI.create(builder.toString()),null, String.class);
        log.info("responseEntity:{}", responseEntity);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");

        log.info("data:{}", data);
        return ResponseResult.success(body);
    }

}
