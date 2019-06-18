

## 参数

- `MYSQL_HOST_IP`      mysql数据库服务器IP
- `MYSQL_HOST_DB`       mysql数据库名称
- `MYSQL_HOST_PORT`     mysql数据库服务器端口
- `MYSQL_HOST_USERNAME`  mysql数据库服务器用户名称
- `MYSQL_HOST_PASSWORD`  mysql数据库服务器用户密码

- `WIKI_FS`  服务器路径 默认：`/tmp/fs`


docker run -itd -e MYSQL_HOST_IP='172.18.0.1' -e MYSQL_HOST_DB='hzq_wiki' -e MYSQL_HOST_PORT=3306 -e MYSQL_HOST_USERNAME=root -e  MYSQL_HOST_PASSWORD=root -p 8080:8080 -v /root/docker/wiki/fs/:/tmp/fs/:z  --name wiki-admin mmd0308/wiki-admin:v1