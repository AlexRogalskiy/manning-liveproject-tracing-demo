docker kill demo
docker rm demo
docker run -d --name demo \
  -p 80:8080 \
  zhaohuabing/liveproject-tracing:v1.1

sleep 5
curl  http://127.0.0.1/checkout
