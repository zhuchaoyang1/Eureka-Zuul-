#Eurake+Zuul+Feign架构  
#项目特点  
- 分布式
- 限流
- 重试机制
- 服务降级
- Token令牌

#架构图  
![avatar](/images/picture.jpg)

#测试效果  
$\color{rgb(255,255,0)}{注：在调用下列除Login接口之外的所有接口都需要先调用Login接口并将Token值放入请求体Header中}$
- 网关屏蔽原服务统一服务名测试  
    - 原始微服务：Consumer通过Postman访问不到
    如接口：http://localhost:8764/consumer/admin/info需要通过
    http://localhost:8764/zhuchaoyang/admin/info才可访问  
    好处：屏蔽原始服务名
    
- 限流测试  
    网关对同一个IP设置了一个窗口期内不能超过100次访问接口以及
    一个窗口期不能超过1000秒的响应时间```本项目中一个窗口期设置为60秒```  
    测试方法：首先使用Postman调用登录接口```localhost:8764/login/user/login```
    复制返回的Token，请求接口：```localhost:8764/zhuchaoyang/admin/info```
    并将上述的Token以Key为token放入该请求的Header中。
    使用Postman连续调用上述第二个请求120次会发现只有一百次成功剩下的失败。
    
- 重试机制测试  
    Zuul Ribbon开启之后又默认的ConnectionTime和ReadTime，当通过Zuul请求
    的接口如果响应时间超出上述两个值，那么可以触发Zuul网关的重试机制。
    测试方式，请求接口：```http://localhost:8764/zhuchaoyang/admin/ribbon```
    效果图：  
    ![avatar](/images/重试机制.jpg)
- 服务降级测试  
    (一定要注意服务降级和服务重试机制的区别)上述的重试机制只是在服务连接超时或者是响应超时会触发，但如果
    Borker提供的服务报错，那么重试机制是做不了的，为了缓解服务中会报错引起用户的不适，才有了服务降级处理。
    测试方法，请求接口：```http://localhost:8764/zhuchaoyang/admin/ribbon```  
    效果图（还是针对该接口，对比与上图的结果区别）  
    ![avatar](/images/服务降级.jpg)
- 权限认证  
    权限包含在登录之后的JWT-Token中  
- 查看用户是否登录授权  
    Zuul过滤器判断Token  

