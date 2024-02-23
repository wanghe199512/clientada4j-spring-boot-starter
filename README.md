# spring-boot-starter-clientada4j

#### 介绍
三方客户端请求接口请求配置工具

#### 软件架构
无

#### 开始使用
```
clientada4j.enabled = true # 开启或关闭
clientada4j.scanProperties #扫描配置文件(默认扫描resources/clientAda目录下所有文件)
clientada4j.mapCls  # 配置与实体关系映射
```
#### 基本使用

##### 一、简单请求示例
```
@Autoware
private DefaultInterfaceClientAda clientAda;
public void request(){
     //接口示例: https://sapi.k780.com/?app=weather.today&weaId=1&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
     ImmutableMap<String, Object> immutableMap = ImmutableMap.<String, Object>builder()
          .put("app", "weather.today")
          .put("weaId", "1")
          .put("appkey", "10003")
          .put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4")
          .put("format", "json")
          .build();
     ExternalResponseProp<ImmutableMap<String, Object>> res = externalInterfaceAda.request("http://api.k780.com", immutableMap);
     System.out.println(res);
}
```

##### 二、复杂请求示例
```
复杂请求模式需配置请求url=http://localhost:8080 serviceCd =/login，程序会自动做拼接并附加动态参数，示例文件在源码：example包下
```

##### 三、自定义响应参数映射示例
```
public <E> ExternalResponseProp<E> request(T prop, String serviceCd, ImmutableMap<String, Object> params, Class<ExternalResponseProp<E>> cls)

public <E> ExternalResponseProp<E> request(String externalId, String serviceCd, ImmutableMap<String, Object> params, Class<ExternalResponseProp<E>> cls)

cls=映射实体
params=动态请求参数
externalId= 配置文件中配置的主服务ID
prop=配置文件中配置信息

ExternalResponseProp.getResponse() 即可返回结果
```

##### 四、自定义请求参数映射示例
```
1.配置 <useAliasAsRequest>true</useAliasAsRequest> // 是否使用别名作为请求，true 开启别名映射 false不使用别名映射
2.
public class CoustomInterfaceAdaAliasAdapter implements InterfaceAdaAliasAdapter {
    /**
     * 头部别名映射
     *
     * @param headers 头部参数
     * @return ImmutableMap<String, Object>
     */
    @Override
    public ImmutableMap<String, Object> headerAlias(ImmutableMap<String, Object> headers) {
        return ImmutableMap.of("header", headers);
    }

    /**
     * 参数别名映射
     *
     * @param params 请求参数
     * @return ImmutableMap<String, Object>
     */
    @Override
    public ImmutableMap<String, Object> paramsAlias(ImmutableMap<String, Object> params) {
        return ImmutableMap.of("body", params);
    }
}
3.externalInterfaceAda.addRequestMappingAliasAdapter(new CoustomInterfaceAdaAliasAdapter());

在某种特定场合，请求参数如以下 {header:{appId:xxx,clientId:xxx},body:{xxx})可使用自定义请求参数映射,修改别名 "body" 通常为实际请求参数


```

#####说明: 不同构造器的使用参照源码文档说明


#####联系作者

有发送问题请发送至邮箱：1280381827@qq.com

--------------------
文档待完善

