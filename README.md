# This app is developed using spring boot restful api. It is using H2 in memory DB for storing the data part.
# To build the project 

mvn clean install

To Run the App

mvn spring-boot:run 

PricedataApplication class sets up data in the the system.

Cache is managed using EhCache /src/main/resources/ehcache.xml

Swagger Endpoint to test the App
http://localhost:8090/swagger-ui.html


Endpoint to getAllPrices for a given Vendor 

Example
http://localhost:8090/api/1.0/getAllPricesByVendor/Bloomberg


Endpoint to getAllPrices for a given Instrument 

Example
http://localhost:8090/api/1.0/getAllPricesByInstrument/EURUSD

Sequence Data

Client --> API EndPoint Restful Controller --> Spring Repository Data 
       <-- Json Response 


Unit Tests 

PriceCacheDataRepositoryTest contains repository tests

PriceDataControllerTest contains unit tests for controllers




Limitations

Ehcache is not evicting entries after TTL.I tried to test it for shorter time duration but cache evict is not working.
Caching is kicked off once you make first call to controller but after TTL it should invoke controller again
to reload the data from the DB.If I spend more time then I can figure out why this is not working.





