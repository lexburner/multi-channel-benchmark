### 测试方式

依赖于我自己实现的一个 RPC 小框架：krpc，使用 netty 模拟了不同连接数下不同的性能。

1. 下载源代码
```java
git clone https://github.com/lexburner/krpc.git
```

2. 打包 maven

```shell
maven install
```

3. 启动 benchmark 测评程序

启动 moe.cnkirito.benchmark.Server，之后运行 moe.cnkirito.benchmark.ClientApp，可以修改 channelNum 来测试不同连接数下的 qps

4. 启动压测脚本

```java
sh benchmark.sh
```

通过控制台的输出观察 qps 的变化，测评脚本的逻辑为一次预热，后 5 次测量，可以观察最高的 qps。

### 个人结论

在如下测评环境下

操作系统：MacOS
内存： 16 GB 2133 MHz LPDDR3

不同 channel 数量下，qps 并没有提升，RPC 通信使用单 channel 即可。
