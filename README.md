# ddd-demo

这是一个基于 DDD（领域驱动设计）思想的 Spring Boot Demo 项目，支持用户的增删改查（CRUD）操作。

## 技术栈
- Spring Boot 3.x
- Spring Data JPA
- H2 内存数据库
- Lombok
- JUnit 5 + MockMvc（集成测试）

## 目录结构
```
src/main/java/com/qihui/ddddemo/
├── DddDemoApplication.java         # 启动类
├── application/                   # 应用服务层
├── domain/                        # 领域层（实体、仓储接口）
├── infrastructure/                # 基础设施层（JPA实现）
├── interfaces/                    # 接口层（REST Controller）
├── common/                        # 通用异常与全局处理
```

## 如何运行
1. **启动项目**
   ```bash
   ./mvnw spring-boot:run
   ```
2. **访问接口**
   - 用户API: `http://localhost:8080/api/users`
   - H2 控制台: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - 用户名: `sa`，密码留空

## 常用API示例
- 创建用户：
  ```http
  POST /api/users
  Content-Type: application/json
  {
    "username": "test",
    "email": "test@example.com",
    "password": "123456"
  }
  ```
- 查询所有用户：
  ```http
  GET /api/users
  ```
- 查询单个用户：
  ```http
  GET /api/users/{id}
  ```
- 更新用户：
  ```http
  PUT /api/users/{id}
  Content-Type: application/json
  {
    "username": "newname",
    "email": "new@example.com",
    "password": "newpass"
  }
  ```
- 删除用户：
  ```http
  DELETE /api/users/{id}
  ```

## 运行集成测试
```bash
./mvnw test
```

## 说明
- 项目采用内存数据库，重启后数据会丢失。
- 测试数据与实际运行数据互不影响。
- 如需持久化数据，可将 H2 配置改为文件模式。
