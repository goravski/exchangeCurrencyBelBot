package org.goravski.exchangeCurrencyBelBot.service;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
/**
 * Factory chosen bank JsonParser
 */
@Component
@Slf4j
public class ConversionFactory {
    private static final Map<BanksType, CurrencyConversionService> conversionServiceMap = new EnumMap<>(BanksType.class);

    @Autowired
    private ConversionFactory(List<CurrencyConversionService> conversionServices) {
        conversionServices.forEach(service -> conversionServiceMap.put(service.getBankType(), service));
    }

    public static CurrencyConversionService getConversionService (BanksType bank){
        log.info("Conversion factory for {}", bank);
        CurrencyConversionService service = conversionServiceMap.get(bank);
        if (service == null){
            log.info("There isn't service for {}", bank);
            throw new IllegalStateException("There isn't service for this bankType");
        }
        return service;
    }
}
