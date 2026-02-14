<h1 align="center">CRYPTOFLOW</h1>

<p align="center">
  <img src="https://images.unsplash.com/photo-1621761191319-c6fb62004040?auto=format&fit=crop&w=1200&q=80" alt="Crypto dashboard visual" width="760" />
</p>

<h2 align="center">Overview</h2>

Cryptoflow is a Spring Boot backend service that fetches live cryptocurrency market data from CoinGecko, caches the latest prices in Redis, and pushes real-time update notifications through Server-Sent Events (SSE).

<h2 align="center">Functionality</h2>

- Fetches market data for a configured list of coins from CoinGecko.
- Runs scheduled refresh jobs every 60 seconds.
- Stores the latest market snapshot in Redis.
- Publishes Redis update events when new data is saved.
- Notifies connected clients via SSE when prices are refreshed.
- Exposes HTTP endpoint for live market data retrieval.

<h2 align="center">Basic Installation</h2>

1. Install Java 21, Maven, and Redis.
2. Clone the repository:

```bash
git clone <your-repo-url>
cd cryptoflow
```

3. Start Redis on `localhost:6379`.
4. Configure values in `src/main/resources/application.properties` if needed.
5. Run the application:

```bash
./mvnw spring-boot:run
```

6. Open API docs at `http://localhost:8080/docs`.
