docker kill demo jaeger
docker rm demo jaeger

docker run -d --name demo \
  -p 80:8080 \
  zhaohuabing/liveproject-tracing:v1.2

docker run -d --name jaeger \
  -p 14268:14268 \
  -p 16686:16686 \
  jaegertracing/all-in-one:1.22

sleep 5
curl  http://127.0.0.1/checkout
