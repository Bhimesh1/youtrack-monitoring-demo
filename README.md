# YouTrack Monitoring Demo

This project is a small demo setup that shows how to monitor a simple metric (`cpu_usage`) using **Grafana** and **Prometheus**.

It was created for the YouTrack monitoring challenge (Task #2). The goal is to collect and visualize metrics through a working Grafana dashboard.


---

### What this project includes

* **Grafana** - dashboard visualization
* **Prometheus** - metric collection
* **Pushgateway** - receives pushed metrics
* **Java metric app** - generates and pushes `cpu_usage` data every few seconds

Everything runs using **Docker Compose** for easy setup and portability.

---

### How it works

1. The **Java app** generates random CPU usage values (10–95%) and pushes them to the **Pushgateway**.
2. **Prometheus** scrapes metrics from the Pushgateway.
3. **Grafana** visualizes the data on a real-time dashboard.

Flow:

```
Java -> Pushgateway -> Prometheus -> Grafana
```

---


## How to run the project

### Step-by-step

**1. Clone the repository**

```
git clone https://github.com/Bhimesh1/youtrack-monitoring-demo.git

cd youtrack-monitoring-demo/demo-prometheus-and-grafana-alerts
```

**2. Build and start the containers**

```
docker compose up -d --build
```

This command will start Prometheus, Grafana, Pushgateway, and the Java metric generator container.

**3. Access the services**

| Service     | URL                                            | Notes               |
| ----------- | ---------------------------------------------- | ------------------- |
| Grafana     | [http://localhost:3000](http://localhost:3000) | user: admin / admin |
| Prometheus  | [http://localhost:9090](http://localhost:9090) | query metrics       |
| Pushgateway | [http://localhost:9091](http://localhost:9091) | view pushed data    |

**4. Import the Grafana dashboard**

* Open Grafana -> Dashboards -> Import.
* Upload `cpu_usage-dashboard.json`.
* Set the data source to Prometheus.

You should see a live graph showing CPU usage values changing over time.

---




### About the Java Metric App

Originally, this demo used a small shell-based CPU generator (a clock-style script) to push data into Pushgateway.
It worked fine for testing, but it was replaced with a **lightweight Java app** to keep the setup cleaner and more relevant to JetBrains’ technology stack.

The Java app demonstrates how a typical backend service (written in Java) could expose or push its metrics to a monitoring pipeline.
This aligns with JetBrain's ecosystem and shows observability using Java tools.

If you want to see it running:

```
docker compose logs -f java_cpu_generator
```

You’ll see logs like:

```
Pushed value: 67
Pushed value: 84
```

---

### Stop the project

```
docker compose down
```

This stops and removes all running containers.

---

### Deliverables

* `cpu_usage-dashboard.json` - Grafana dashboard definition
* `docker-compose.yaml` - environment configuration
* `java-metric/` - Java app (source + Dockerfile)
* `README.md` - setup and usage instructions

---

### Project Summary

This demo sets up a full monitoring pipeline showing how data flows from a Java service into Grafana via Prometheus and Pushgateway.
It fulfills the **YouTrack Monitoring Challenge Task #2** requirements by providing:

* A functional Grafana dashboard
* Working metric (`cpu_usage`) with live updates
* A public Git-based observability-as-code setup
* Java integration for relevance and real-world applicability.

In short: **A clean, reproducible monitoring stack that shows metrics from a Java service inside Grafana.**
