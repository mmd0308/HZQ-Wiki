cd ../wiki-ui/

npm run build

cd ../wiki-admin/

rm -rf  src/main/resources/static/*

cp -r ../wiki-ui/dist/* ../wiki-admin/src/main/resources/static/

mvn clean package -Dmaven.test.skip=true

docker rmi mmd0308/wiki-admin:v1.0.0

docker build -t mmd0308/wiki-admin:v1.0.0 .


