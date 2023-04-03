# Spring Boot Prometheus & Grafana Demo

A very minimalist example, that show how to configure Grapahana to scrap Spring Boot app metrics and shows them in grafana dashboards.

## Attention

* Make sure the local IP of your computer and the label for the app name is set in `prometheus.yml`. Otherwise scraping won't work properly.

```
static_configs:
      - targets: ['192.168.42.007:8080']
        labels:
          application: spring-boot-app
```

* If you access http://localhost:8080/actuator/prometheus you should see the metrics in the prometheus format.

## How to run

To run the examole use `docker-compose up -d`, this will start 2 containers: Prometheus and Grafana. Next on, run Spring boot app with  `gradle bootRun`!

## Links

* Spring Boot App - http://localhost:8080/actuator/prometheus
* Prometheus - http://localhost:9090
* Grafana - http://localhost:3000
* Grafana Dashboards - https://grafana.com/dashboards?search=spring%20boot

## Working Dashboards

* https://grafana.com/dashboards/4701

