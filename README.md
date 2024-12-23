Spring Boot Backend for Real-Time Stock Prices

This Spring Boot project uses the Finnhub API to fetch real-time stock prices. Below is the documentation to help you understand and run the project.

Features:
1. Fetch real-time stock prices using the Finnhub API.
2. Easy-to-use Spring Boot framework.
3. Designed for scalability and future enhancements.

Prerequisites :
Java 17 or higher: Ensure Java is installed and configured on your machine.
Maven: Installed for dependency management.
Finhub API Key: A valid Finhub API key is required to use this application.

Steps to Run the Project Locally :
Clone the Repository :
git clone <repository-url>
cd <repository-folder>

Set Up Environment Variables
Add your Finnhub API key as an environment variable or in the application.properties file:
finnhub.api.key=your_api_key_here
Or
You can add it directly in the StockService.java file:
private String apiKey=your_api_key_here;

Build the Project :  mvn clean install

Run the Application : mvn spring-boot:run

Access the Application : The application runs at http://localhost:2024.

Assumptions and Limitations :

Assumptions:
1. The user has a valid Finnhub API key.
2. The stock symbols used in requests are valid and supported by the Finhub API.

Limitations:
1. The application fetches real-time prices but does not store historical data.
2. Rate-limiting is dependent on the Finhub API’s free/paid tier restrictions.

Links :
Deployed Application: Add the URL if deployed (e.g., https://your-deployment-url)

API Documentation: Add the URL to live API docs (e.g., Swagger UI if implemented).

Troubleshooting :
Issue: Unable to fetch data.
Solution: Check the validity of the Finnhub API key and ensure it’s correctly set in the application.

Issue: Application not starting.
Solution: Ensure Java and Maven versions are compatible and check for any errors during the build process.
