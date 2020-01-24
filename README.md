# Spring Boot Reactive Client for YugabyteDB Cluster Query Language (YCQL)

This app is a fully reactive Spring Boot implementation backed by YugabyteDB Cluster Query Language (YCQL). YugabyteDB natively supports reactive programming model using Apache Cassandra Reactive driver.

`spring-reactive-ycql-client` app uses the following components:

- Spring WebFlux
- Spring Data Cassandra Reactive
- Three Node YugabyteDB cluster

App is a REST based application which exposes REST APIs for CRUD operations on a Product domain, reveiew the following class `com.yugabyte.ycql.sample.domain.Product`. 


# Environment Setup


## Step 1: Start the YugabyteDB cluster

You can do so using following command from YugabyteDB installation directory,


```
$ ./bin/yb-ctl destroy && ./bin/yb-ctl --rf 3 create --tserver_flags="cql_nodelist_refresh_interval_secs=10" --master_flags="tserver_unresponsive_timeout_ms=10000"
```

This will start a 3-node local cluster with replication factor (RF) 3. The flag cql_nodelist_refresh_interval_secs configures how often the drivers will get notified of cluster topology changes and the following flag tserver_unresponsive_timeout_ms is for the master to mark a node as dead after it stops responding (heartbeats) for 10 seconds.

Note: (Detailed installation instructions)[https://docs.yugabyte.com/latest/quick-start/install/#macos] for YugabyteDB on local workstation.

## Step 2: Initialize YugabyteDB

you can do so by executing the following command:

```
$ ./bin/cqlsh -f schema.sql
```

# Build and Run the application

## Step 1: Build the spring boot application

To build the app, execute the following maven command from the project base directory:

```
$ ./mvnw clean pacakage -DskipTests
```

## Step 2: Start the application

you can do so by running the following command:

```
$ java -jar target/spring-reactive-ycql-client-0.0.1-SNAPSHOT.jar
```

# Working with REST endpoints

## Step 1: Create a product

You can create a product listing as follows:
```
$ curl \
  --data '{ "productName": "Notebook", "description": "200 page notebook", "price": 7.50 }' \
  -v -X POST -H 'Content-Type:application/json' http://localhost:8080/products/save
```

You should see the following return value:
```
{
  "productId": "1",
  "productName": "Notebook",
  "description": "200 page, hardbound, blank notebook",
  "price": 7.5}
```
You can connect to YugaByte DB using `cqlsh` and select these records:

```
$ ./bin/cqlsh

cqlsh> select * from sample.products;

 productid | productname | description       | price
-----------+-------------+-------------------+-------
      abc1 |    Notebook | 200 page notebook |   7.5
```

## Step 2: Retrieve the product using ID

You can retrieve a product using ID as follows:

```
$ curl http://localhost:8080/products/abc1
```

You should see the following:
```
{
  "productId": "1",
  "productName": "Notebook",
  "description": "200 page, hardbound, blank notebook",
  "price": 7.5}
```

## Step 3: Delete the product using ID 

You can delete a product using ID as follows:

```
$ curl http://localhost:8080/products/delete/abc1
```

You should see the following:
```
{
  "productId": "1",
  "productName": "Notebook",
  "description": "200 page, hardbound, blank notebook",
  "price": 7.5}
```




