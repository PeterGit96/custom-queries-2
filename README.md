# Exercise - Spring Boot - Custom Queries 2
* write a Spring Boot application with the necessary dependencies that:
  * uses the same entity and repository as `Custom Queries 1` exercise
  * has a `FlightController`:
    * mapped on `flights`
    * for the provisioning of `n` flights (where `n` is an optional query param; if absent, `n=100`):
      * all the string values are randomly generated (using `random.ints()`)
      * the `status` is generated randomly
    * for retrieving all the flights in the db using pagination and returning them in ascending order by `fromAirport`
    * for retrieving all the flights that are `ONTIME` without using a custom query 
    * for retrieving - using a custom query - all the flights that are in `p1` or in `p2` status
      * consider that the user has to pass `p1` and `p2` as parameters to the `GET` request
* test the endpoints with `Postman`:
  * for provisioning without the `n` query parameter
  * for provisioning with the `n` query parameter, with value `49`
  * for getting the flights using pagination
  * for getting the flights that are `ONTIME`
  * for getting the delayed or cancelled flights
  * for getting the on time or delayed flights
* **note for reviewers**: view `CustomQueries2.postman_collection.json` in the root folder for all the `Postman` calls
