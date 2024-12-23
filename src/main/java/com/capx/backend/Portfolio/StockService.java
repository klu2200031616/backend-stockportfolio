package com.capx.backend.Portfolio;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class StockService {

    private final StockRepository stockRepository;
  

  
    private String apiKey="ctklgbpr01qn6d7itd7gctklgbpr01qn6d7itd80";

    public StockService(StockRepository stockRepository, RestTemplate restTemplate) {
        this.stockRepository = stockRepository;
        this.restTemplate = restTemplate;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock addStock(Stock stock) {
        stock.setCurrentPrice(fetchRealTimePrice(stock.getTicker())); // Set the initial price when adding a stock
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
    
    public Stock updateStock(Long id, Stock stock) {
        Stock existingStock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
        existingStock.setStockName(stock.getStockName());
        existingStock.setTicker(stock.getTicker());
        existingStock.setQuantity(stock.getQuantity());
        existingStock.setBuyPrice(stock.getBuyPrice());
        return stockRepository.save(existingStock);
    }

    public double calculatePortfolioValue() {
        List<Stock> stocks = stockRepository.findAll();
        double totalValue = 0.0;

        for (Stock stock : stocks) {
            String ticker = stock.getTicker();
            double currentPrice = fetchRealTimePrice(ticker);
            System.out.println(currentPrice);
            totalValue += currentPrice * stock.getQuantity();
        }
        return totalValue;
    }


    private RestTemplate restTemplate = new RestTemplate();


  
//ctklgbpr01qn6d7itd7gctklgbpr01qn6d7itd80


    public double fetchRealTimePrice(String ticker) {
        // URL for the Finnhub Real-time quote endpoint
        String url = String.format(
                "https://finnhub.io/api/v1/quote?symbol=%s&token=%s",
                ticker, apiKey);
        System.out.println("Fetching URL: " + url);

        try {
            // Call the API and get the raw response
            String response = restTemplate.getForObject(url, String.class);

            System.out.println("Response received: " + response); // Log the raw response

            // Check if response is empty or null
            if (response == null || response.isEmpty()) {
                throw new RuntimeException("Error: Empty response received for ticker: " + ticker);
            }

            // Using Jackson to parse the response into JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            // Extract the 'c' field which represents the current price
            String price = rootNode.path("c").asText();
            System.out.println("The price is: " + price);

            // Check if price is available
            if (!price.isEmpty()) {
                return Double.parseDouble(price);
            } else {
                throw new RuntimeException("Error: No price data available for ticker: " + ticker);
            }

        } catch (Exception e) {
            // Log the error and rethrow it
            System.err.println("Error fetching stock price for ticker: " + ticker);
            e.printStackTrace();
            throw new RuntimeException("Error fetching stock price for ticker: " + ticker, e);
        }
    }
}
