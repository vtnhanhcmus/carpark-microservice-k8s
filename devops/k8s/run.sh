kubectl --namespace default scale deployment $(kubectl --namespace default get deployment | awk '{print $1}') --replicas 0
kubectl delete svc --all

kubectl apply -f 1_configmaps.yml
kubectl apply -f 2_zipkin.yml
kubectl apply -f 3_configserver.yml
kubectl apply -f 4_eurekaserver.yml
kubectl apply -f 5_apigateway.yml
kubectl apply -f 6_carpark_db.yml
kubectl apply -f 7_accounts.yml
kubectl apply -f 8_carpark_etl.yml