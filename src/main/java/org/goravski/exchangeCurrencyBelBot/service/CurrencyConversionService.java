package org.goravski.exchangeCurrencyBelBot.service;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

import java.net.HttpURLConnection;

/**
 * Interface must get BankConnections and class implemented RateJsonParser and realize conversion currency
 */
public interface CurrencyConversionService {

    BanksType getBankType();

    @SneakyThrows
    default JsonStructure getJsonStructure(HttpURLConnection connection) {
        JsonReader reader = Json.createReader(connection.getInputStream());
        return reader.read();
    }

    /**
     * @param original - original currency  Enum object
     * @param target   - target currency  Enum object
     * @return - relation index of changing currency
     */
    default double getConversionRatio(CurrencyName original, CurrencyName target) {
        double originalRate = getBuyRate(original);
        double targetRate = getSaleRate(target);
        return originalRate / targetRate;
    }

    /**
     * using implemented RateJsonParser get parsed currency rate
     * @param currency - currency  Enum object
     * @return - buy rate value currency
     */
    double getBuyRate(CurrencyName currency);

    /**
     * using implemented RateJsonParser get parsed currency rate
     * @param currency - currency  Enum object
     * @return - sale rate value currency
     */
    double getSaleRate(CurrencyName currency);

}
