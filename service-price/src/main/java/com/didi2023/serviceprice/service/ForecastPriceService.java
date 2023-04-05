package com.didi2023.serviceprice.service;

import com.didi2023.internalcommon.dto.ForecastPriceDTO;
import com.didi2023.internalcommon.dto.PriceRule;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.DirectionResponse;
import com.didi2023.internalcommon.response.ForecastPriceResponse;
import com.didi2023.internalcommon.util.BigDecimalUtils;
import com.didi2023.serviceprice.mapper.PriceRuleMapper;
import com.didi2023.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);

        System.out.println("距离,时间");
        System.out.println(direction.getData().getDistance());
        System.out.println(direction.getData().getDuration());

        log.info("读取计价规则");
        HashMap<String, Object> map = new HashMap<>();
        map.put("city_code", "10010");
        map.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(map);
        if (Objects.isNull(priceRules) || priceRules.size() == 0) {
            log.error("计价规则不存在");
            return ResponseResult.fail("计价规则不存在");
        }
        log.info("计价规则:{}", priceRules.get(0));



        log.info("根据距离,时间,计价规则计算价格");
        double price = getPrice(direction.getData().getDistance(), direction.getData().getDuration(), priceRules.get(0));
        forecastPriceResponse.setPrice(price);

        return ResponseResult.success(forecastPriceResponse);
    }

    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        double price = 0;

        // 起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(startFare, price);

        //(里程转换成千米 - 起步里程 ) * 单价 = 里程费
        //里程不超过起步里程,里程费为0
        double distanceKm = BigDecimalUtils.div(distance, 1000, 2);
        double distanceSubtract = BigDecimalUtils.sub(distanceKm, priceRule.getStartMile());
        double mile = distanceSubtract > 0 ? distanceSubtract : 0;
        //里程单价
        double mileFare = BigDecimalUtils.mul(mile, priceRule.getUnitPricePerMile(),2);
        price = BigDecimalUtils.add(price, mileFare);

        //时长转换成分钟 * 单价 = 时长费
        double timeMinute = BigDecimalUtils.div(duration, 60, 2);
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        double timeFare = BigDecimalUtils.mul(timeMinute, unitPricePerMinute,2);
        price = BigDecimalUtils.add(price, timeFare);

        return price;

    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(2);
//        priceRule.setUnitPricePerMile(1.0);
//        priceRule.setUnitPricePerMinute(0.5);
//        double price = getPrice(9500, 60, priceRule);
//        System.out.println(price);
//    }
}
