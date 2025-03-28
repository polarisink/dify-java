# Dify-Java API使用说明

---

## 项目概述

本项目基于 Spring Boot 3.2.x 实现与 Dify 平台的 API 集成，采用 **HTTP Interface** 声明式客户端，提供简洁高效的 API
调用方式。通过本框架，开发者可快速完成以下功能：

- Dify 工作流执行接口封装
- 文件上传与 API 密钥管理集成
- 响应流式/阻塞模式切换

---

## 环境要求

```
JDK 17+
Spring Boot 3.2.x+
Maven 3.6+
Dify API 密钥（从平台控制台获取）
```

---

## 快速开始

### 1. 添加依赖

- maven包上架还未完成，可以下载源码手动编译

```xml
<!-- Spring Web & HTTP Interface -->
<dependency>
    <groupId>com.github.polarisink</groupId>
    <artifactId>dify-spring-boot-starter</artifactId>
    <version>latest</version>
</dependency>
```

- 项目基于webflux编写，需要注意处理和web的冲突
- 如果是spring-web项目则正常使用
- 如果在非spring环境中，可以使用`HttpInterfaceUtil`创建XXXApi的代理类，或使用XXXClient的builder方法构建Client

### 2. 使用

#### 1、聊天室，工作流只有单个的情况

- 在 `application.yml` 中配置：

```yaml
dify:
  base-url: https://api.dify.ai/v1
  chat-key: app-xxxxxxxxxx  # 从Dify控制台获取
  text-key: app-xxxxxxxxxx  # 从Dify控制台获取
  dataset-key: dataset-xxxxxxxxxx  # 从Dify控制台获取
  workflow-key: app-xxxxxxxxxx  # 从Dify控制台获取
```

- 在业务中进行使用

```java
//启用dify自动配置
@EnableDifyApi
@RequestMapping("/dify")
@RestController
class ChatController {
    //其他的几个API：DifyDatasetApi DifyTextApi DifyWorkflowApi同理
    @Autowired
    private DifyChatApi difyChatApi;

    @GetMapping("/chat")
    public DifyChat chat(@RequestBody DifyChatRequest paramMessage) {
        return difyChatApi.chat(paramMessage);
    }
}

```

#### 2、有多个聊天室或工作流

- 手动构建`DifyChatClient`进行调用，此时不能再使用`DifyChatApi`等系列api，而且client系列

```java
@RequestMapping("/dify")
@RestController
class ChatController {
    //其他的几个API：DifyDatasetApi DifyTextApi DifyWorkflowApi同理
    private String baseUrl = "https://api.dify.ai/v1";
    private String token = "token";
    //使用map维护多个不同的token
    private static final  Map<String, DifyChatClient> chatMap = new ConcurrentHashMap<>();

    @GetMapping("/chat")
    public DifyChat chat(@RequestBody DifyChatRequest paramMessage) {
        DifyChatClient difyChatClient = chatMap.computeIfAbsent(token, k -> DifyChatClient.builder().baseUrl(baseUrl).token(token).build());
        return difyChatClient.chat(paramMessage);
    }
}
```

---

## 配置说明

| 配置项                 | 示例值                      | 说明               |
|---------------------|--------------------------|------------------|
| `dify.chat-key`     | `app-xxxxxxxx`           | 控制台获取的聊天室api-key |
| `dify.workflow-key` | `app-xxxxxxxx`           | 控制台获取的工作流api-key |
| `dify.base-url`     | `https://api.dify.ai/v1` | API 基础路径         |

---

## 注意事项

2. **文件类型限制**：上传文件需符合 Dify 的 MIME 类型要求（如 TXT/PDF）
3. **错误代码处理**：捕获 `HttpClientErrorException` 处理 4xx/5xx 错误
4. **流式响应**：设置 `response_mode=streaming` 时需使用 WebClient 处理 SSE

---

## 任务列表

- [ ] spring-boot-starter上架maven中心仓库
- [ ] sse相关api完善及测试
- [ ] 测试用例的编写

完整代码示例可参考测试程序，建议结合 [Dify](https://docs.dify.ai/zh-hans) 官方文档进行调试。