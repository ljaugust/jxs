# jxs
2020.5.9更新——京鲜生项目(vue+spring cloud+mybatis-plus+spring security oauth2.0)

### 项目介绍

   jxs是基于vue + springcloud + mybatisplus的项目。

### 使用说明


1.新建数据库，导入数据库脚本（jxs.sql），修改yml文件的数据库用户名密码，修改mybatis-plus配置文件

2.启动jxs-server 注册中心

3.启动jxs-zuul 网关

4.启动jxs-client 客服端微服务

5.启动jxs-uaa 权限认证微服务（账号：lj   密码：lj123）

6.启动前端项目（npm run serve）
  页面UI设计参考网站 [果鲜园](http://www.guoxianyuan.cn/)

### 系统截图
![登录页面](https://github.com/ljaugust/jxs/blob/master/login.png)
![管理员登录页面](https://github.com/ljaugust/jxs/blob/master/adminlogin.png)
![首页](https://github.com/ljaugust/jxs/blob/master/home.png)
![水果分类](https://github.com/ljaugust/jxs/blob/master/sort.png)
![水果详情](https://github.com/ljaugust/jxs/blob/master/show.png)
![购物车页面](https://github.com/ljaugust/jxs/blob/master/cart.png)
![我的订单页面](https://github.com/ljaugust/jxs/blob/master/order.png)
![提交水果](https://github.com/ljaugust/jxs/blob/master/admincenter.png)
