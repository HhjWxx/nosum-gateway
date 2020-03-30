## 项目打包

```bash
mvn clean package -Dmaven.test.skip=true
```

> 打包后会有俩个`*.jar`,使用`nosum-gateway-v1-jar-with-dependencies.jar`

## 运行项目

> `windows`环境下进入到 `target`中，使用如下命令

```bash
java -jar nosum-gateway-v1-jar-with-dependencies.jar
```

> `linux`环境下，将`nosum-gateway-v1-jar-with-dependencies.jar`与`nosum-gateway.sh`上传到同一目录中，进入到文件所在目录，执行如下命令

```bash
sh nosum-gateway.sh
```

## 下载地址

> `github`地址`https`

```text

```

> `github`地址`ssh`

```text

```

> 个人远程仓库`https`

```text
https://git.nosum.cn/nosum/nosum-gateway.git
```

> 个人远程仓库`ssh`

```text
root@git.nosum.cn:nosum/nosum-gateway.git
```

## 关于扩展

> 参与`dubbo`中的`SPI`
>
> > 所有具备`@SPI`注解的接口，可以在`resources/META-INF/gateway`中新建以接口名为文件名的文件进行扩展

## 内置扩展

> `cn.nosum.common.extension.ExtensionFactory`
>
> > 扩展点工厂类，默认使用`adaptive`

```properties
adaptive=cn.nosum.common.extension.factory.AdaptiveExtensionFactory
spi=cn.nosum.common.extension.factory.SpiExtensionFactory
```

>`cn.nosum.gateway.container.GateWayContainer`
>
>> 网关容器，默认使用`netty`

```properties
netty=cn.nosum.gateway.container.NettyGateWayContainer
```

> `cn.nosum.gateway.handler.build.HandlerBuilder`
>
> > 容器`handler`构建器，默认使用`context`

```properties
context=cn.nosum.gateway.handler.build.ContextHandlerBuilder
```

> `cn.nosum.gateway.slot.build.SlotChainBuilder`
>
> > 处理槽构建器，默认使用`context`

```properties
context=cn.nosum.gateway.slot.build.LinkSlotChainBuilder
```

> `cn.nosum.gateway.slot.ProcessorSlotChain`
>
> > 处理槽链路类型，默认使用`link`

```properties
link=cn.nosum.gateway.slot.LinkProcessorSlotChain
```

## `application.properties`

> 项目核心配置文件

```properties
# 容器端口号
container.port = 8888
request.file.save.folder.name = /usr/local/gateway/request/file
# 示例配置，即使不配置也是默认使用这些
cn.nosum.common.extension.ExtensionFactory = adaptive
cn.nosum.gateway.container.GateWayContainer = netty
cn.nosum.gateway.handler.build.HandlerBuilder = context
cn.nosum.gateway.slot.build.SlotChainBuilder = context
cn.nosum.gateway.slot.LinkProcessorSlotChain = link
```

## `GateWay `启动入口

```java
public class NoSumGateWayStart {
    public static void main(String[] args) {
        GateWayContainer container = ExtensionLoader
                    .getExtensionLoader(GateWayContainer.class)
                    .getAdaptiveExtension();
        container.start();
    }
}
```

## 未实现的功能

> 负载均衡、熔断、缓存、限流、鉴权

