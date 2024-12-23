package com.capx.backend.Portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinnhubResponse {

    @JsonProperty("c")
    private double currentPrice;

    @JsonProperty("h")
    private double highPrice;

    @JsonProperty("l")
    private double lowPrice;

    @JsonProperty("o")
    private double openPrice;

    @JsonProperty("pc")
    private double previousClosePrice;

    // Getters and setters for all fields
    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getPreviousClosePrice() {
        return previousClosePrice;
    }

    public void setPreviousClosePrice(double previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }
}
