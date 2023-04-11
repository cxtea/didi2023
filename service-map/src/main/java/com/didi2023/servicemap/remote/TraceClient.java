package com.didi2023.servicemap.remote;

import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.dto.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TraceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TraceClient {

    @Value("${amap.key}")
    private String amapKey;

    @Value("${amap.sid}")
    private String sid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<TraceResponse> add(AmapDTO amap){
        String tid = amap.getTid();


        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.TRACE_ADD_URL)
                .append("?key=")
                .append(amapKey)
                .append("&sid=")
                .append(sid)
                .append("&")
                .append("tid=")
                .append(tid);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toString(),null,String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String trid = data.getString("trid");
        String trname = data.getString("trname");

        TraceResponse traceResponse = new TraceResponse();
        traceResponse.setTrid(trid);
        traceResponse.setTrname(trname);


        return ResponseResult.success(traceResponse);
    }
}
