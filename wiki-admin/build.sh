
cd ../wiki-ui/

npm run build

cd ../wiki-admin/

rm -rf  src/main/resources/static/*

cp -r ../wiki-ui/dist/* ../wiki-admin/src/main/resources/static/

mvn clean package -Dmaven.test.skip=true

# version=v2.0.0

# docker rmi mmd0308/wiki-admin:$version

docker build -t mmd0308/wiki-admin:v2.0.0-beta.2 .

rm -rf  src/main/resources/static/*

