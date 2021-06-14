BASEDIR=$(dirname "$0")

if [ -z "$ISTIO_VERSION" ]
then
  export ISTIO_VERSION=1.10.1
fi

istioctl version || (curl -L https://istio.io/downloadIstio | ISTIO_VERSION=$ISTIO_VERSION  sh - && sudo mv $PWD/istio-$ISTIO_VERSION/bin/istioctl /usr/local/bin/)
istioctl install -y -f $BASEDIR/istio-config.yaml
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.10/samples/addons/jaeger.yaml
kubectl label namespace default istio-injection=enabled --overwrite=true
