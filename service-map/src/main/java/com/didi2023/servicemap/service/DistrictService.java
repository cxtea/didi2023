package com.didi2023.servicemap.service;

import com.didi2023.internalcommon.dto.DicDistrict;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.servicemap.mapper.DicDistrictMapper;
import com.didi2023.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;
    public ResponseResult initDistrict(String keywords, String subdistrict){

        String responseBody = mapDicDistrictClient.initDistrict(keywords);

        JSONObject districtResult = JSONObject.fromObject(responseBody);
        int status = districtResult.getInt("status");
        if(status != 1){
            return ResponseResult.fail("获取行政区域失败");
        }
        JSONArray countryArray = districtResult.getJSONArray("districts");
        for (JSONObject countryObj: (Iterable<JSONObject>) countryArray) {
            String addressCode = countryObj.getString("adcode");
            String addressName = countryObj.getString("name");
            String parentAddressCode = "0";
            Integer level = generateLevel(countryObj.getString("level"));

            generateDistrict(addressCode, addressName, parentAddressCode, level);

            JSONArray provinceArray = countryObj.getJSONArray("districts");
            for (JSONObject provinceObj: (Iterable<JSONObject>) provinceArray) {
                addressCode = provinceObj.getString("adcode");
                addressName = provinceObj.getString("name");
                parentAddressCode = countryObj.getString("adcode");
                level = generateLevel(provinceObj.getString("level"));

                generateDistrict(addressCode, addressName, parentAddressCode, level);

                JSONArray cityArray = provinceObj.getJSONArray("districts");
                for (JSONObject cityObj: (Iterable<JSONObject>) cityArray) {
                    addressCode = cityObj.getString("adcode");
                    addressName = cityObj.getString("name");
                    parentAddressCode = provinceObj.getString("adcode");
                    level = generateLevel(cityObj.getString("level"));

                    generateDistrict(addressCode, addressName, parentAddressCode, level);

                    JSONArray districtArray = cityObj.getJSONArray("districts");
                    for (JSONObject districtObj: (Iterable<JSONObject>) districtArray) {

                        if ("street".equals(districtObj.getString("level"))) {
                            continue;
                        }
                        addressCode = districtObj.getString("adcode");
                        addressName = districtObj.getString("name");
                        parentAddressCode = cityObj.getString("adcode");
                        level = generateLevel(districtObj.getString("level"));

                        generateDistrict(addressCode, addressName, parentAddressCode, level);

                    }

                }

            }

        }

        return ResponseResult.success();
    }

    private Integer generateLevel(String level) {
        switch (level ){
            case "province":
                return 1;
            case "city":
                return 2;
            case "district":
                return 3;
            case "country":
            default:
                return 0;

        }

    }

    private void generateDistrict(String addressCode, String addressName, String parentAddressCode, Integer level){
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        dicDistrict.setParentAddressCode(parentAddressCode);
        dicDistrict.setLevel(level);

        dicDistrictMapper.insert(dicDistrict);

    }
}
