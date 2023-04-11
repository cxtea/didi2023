package com.didi2023.servicemap.remote;

import com.didi2023.internalcommon.constant.AmapConfigConstants;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TerminalResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TerminalClient {

    @Value("${amap.sid}")
    private String sid ;

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;
    public ResponseResult add(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(AmapConfigConstants.TERMINAL_ADD_URL)
                .append("?")
                .append("name=")
                .append(name)
                .append("&")
                .append("sid=")
                .append(sid)
                .append("&")
                .append("key=")
                .append(amapKey);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.toString(), null, String.class);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");

        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);


        return ResponseResult.success(terminalResponse);
    }
}
