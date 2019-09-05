# wiki-ui
  HZQ-WIKI 前端项目

## 项目启动

### 获取代码
```git
git clone https://github.com/mmd0308/HZQ-Wiki.git
```
### 安装依赖
```bash
cd wiki-ui
```

```bash
npm install --registry=https://registry.npm.taobao.org
```
### 启动

```bash
npm run dev
```



## npm 代理配置

```
npm config set https-proxy http://server:port
npm config set proxy http://server:port

npm config delete proxy
npm config delete https-proxy
```