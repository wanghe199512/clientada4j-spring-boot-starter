# clientada4j-spring-boot-starter

#### 介绍
clientada4j-spring-boot-starter: 一个重新定义系统接口对接的新实践和新规范。没错，它基于httpClient，我虽然没有能力创造，但我有能力踩在巨人的肩膀.....


#### 软件架构
无

#### 配置项
```
clientada4j.enabled = true # 开启或关闭
clientada4j.default-max-per-route-total #每个路由默认最大连接数
clientada4j.pooling-connection-max-total  # 最大连接数
clientada4j.connectTimeOut  # 连接超时
clientada4j.socketTimeOut  # 响应超时
```
#### 基本使用

##### 如何定义请求？
```
import com.clientAda4j.ClientAdaClaimExecutor;
import com.clientAda4j.anno.ClientAdaComponent;
import com.clientAda4j.anno.ClientAdaInterface;
import com.clientAda4j.domain.ClientResponseProp;
import org.springframework.beans.factory.annotation.Autowired;

@ClientAdaComponent(clientUrl = "https://sapi.k780.com/", clientName = "实时天气", clientHeaderAdapter = TYCClientHeaderAdapter.class)
public class ClientAdaWeatherService {

    @Autowired
    private ClientAdaClaimExecutor claimExecutor;

    @ClientAdaInterface(interfaceName = "实时天气V1", interfaceUri = "")
    public void baseinfo(Object map) {
        ClientResponseProp<DefaultClientResponseProp2> execute = claimExecutor.executeResponseCls(map, DefaultClientResponseProp2.class);
        System.out.println(execute);
    }
}
```
在上述代码中，我们定义了一个访问天气的API接口，并且注入ClientAdaClaimExecutor执行器执行相应的请求，下面我会详细说明每一项的具体定义<br />

###### 一、@ClientAdaComponent注解说明:<br />
```
clientUrl 基础客户端URL(通常我们认为一个系统的基础url及端口不会发生改变，使用该注解，该类中所有请求都会在地址之前加上url访问))<br />
clientName: 描述该类中连接的客户系统名称<br />
clientId: 客户端ID (预留)<br />
clientPort: 请求服务端口 (如果访问的是类似于127.0.0.0:8099这类型的地址，需要指定端口)<br />
clientHeaderAdapter: 自定义请求服务头适配器 (通常用于在请求头中添加Token或改变header时配置)  <br />
```
那么我们该如何使用[自定义请求服务头适配器]呢？使用方式如下:
```
import com.clientAda4j.IClientHeaderAdapter;
import org.apache.http.message.BasicHeader;

public class TYCClientHeaderAdapter implements IClientHeaderAdapter {
    @Override
    public BasicHeader[] adapter() {
        return new BasicHeader[]{new BasicHeader("Content-Type", "application/json;charset=utf-8"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)"),
                new BasicHeader("Connection", "Keep-Alive"),
                new BasicHeader("Accept", "application/json;charset=utf-8"),
                new BasicHeader("Authorization", "949a5bc135e416d8f6f9d6b3e9602e42")};
    }
}
```
直接实现IClientHeaderAdapter，并且重写adapter方法即可，但有时请求时需要携带动态Token(#比如先登录获取Token然后再请求其他接口#)怎么办呢？<br />

**方式一**: 如果你的Token变化不频繁，理论上讲让ClientAdaWeatherService实现ClientAdaWeatherService也是可以的<br />
**方式二**: 如果你的Token变化频繁，甚至每次请求都需要新Token,这个时候就需要**ClientAdaClaimExecutor**来帮忙啦，这个会在下面的章节中提到

###### 二、@ClientAdaInterface注解说明:<br />
```
interfaceName: 描述该类中连接的客户接口名称<br />
interfaceUri: 接口Url <br />
LinkedHashMapClientAdaResponseFactory: 返回结果处理器 【默认LinkedHashMapClientAdaResponseFactory】<br />
interfaceId: 接口ID (预留)<br />
```

有的时候我们可能需要自定义返回，比如接口返回的实体字段与客户系统返回的规范或名称不一致，可以自定义**返回结果处理器**处理,使用也很简单:

```
import com.clientAda4j.IClientAdaResponseFactory;

public class ClientAdaResponseFactory implements IClientAdaResponseFactory<DefaultClientResponseProp2> {
    @Override
    public DefaultClientResponseProp2 process(String response) {
        // ... 自己的业务处理逻辑
        return null;
    }
}
```

response是接口返回的结果，在process()处理完业务逻辑后,亦可使用泛型返回结果,此时接口返回为ClientResponseProp<?> 需要做强制类型转换。

###### 三、ClientAdaClaimExecutor说明:<br />

ClientAdaClaimExecutor作为唯一的执行器，定义了四个方法
```
ClientResponseProp<?> executeResponseFactory(Object args): 基于响应工厂的执行<br />
ClientResponseProp<E> executeResponseCls(Object args, Class<E> responseCls):  基于自定义返回Class执行<br />
ClientResponseProp<LinkedHashMap<String, Object>> execute(String domainUrl, ImmutableMap<String, Object> args) 直接请求<br />
ClientResponseProp<E> execute(String domainUrl, ImmutableMap<String, Object> args, Class<E> responseCls) 直接请求<br />
```

简单概括就是:
1. 如果自定义了 **返回结果处理器** ,使用executeResponseFactory请求,该方式默认使用LinkedHashMapClientAdaResponseFactory,返回结果为HashMap
2. 如果需要返回指定实体类型，使用executeResponseCls请求，并指定responseCls
3. 在实际需求中，比如统一身份认证时，第一步请求获取签名或Token(视实际业务而定),第二步再认证,此时可以用execute方法，显而易见，一个带responseCls一个不带，具体的用法各位自行斟酌


####联系作者

有发送问题请发送至邮箱：1280381827@qq.com, 欢迎issues

就不弄什么官网了，服务器还蛮贵的，主打一个省钱.......

--------------------

