# IBKR Client

### To-dos

* [x] Parameterize private repo in pom.xml
* [x] Parameterize projectId in SecretClient as program argument
* [x] Change setIsConnected into a callback
* [ ] Create IndexTicker type, to support Index data type
* [ ] Implement insert batching handler impl.
* [ ] Use upserts in place of inserts
* [ ] Turn MIDPOINT/TRADES into enum
* [ ] Add documentations about `environment.properties` and  `--projectId=` program argument
* [ ] Implement dead letter queue for failed job reporting
  * [ ] Cater end program criteria to failed jobs
* [ ] Implement job timeout failure (for better automation stability)
  * [ ] Cater end program criteria to timeout jobs
* [ ] Concurrency static analysis
  * [ ] Use concurrency static analysis tools
  * [ ] Review logic and check critical sections
  * [ ] Implement proper locking mechanisms if necessary
* [ ] Database connection pooling (probably not required yet)
* [ ] Replace orderId impl. to support multiple clients (not required yet)

