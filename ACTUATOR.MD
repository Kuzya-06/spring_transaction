# Spring Actuator
## Зависимость
### Maven
```text
      <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
### Gradle
```text
dependencies {
compile("org.springframework.boot:spring-boot-starter-actuator")
}
```

### Адрес
```bash
curl http://localhost:8088/actuator/health
curl http://localhost:8088/actuator/metrics
curl http://localhost:8088/actuator/loggers
```

| ID           | 	Описание                                                                                                                                                              |
|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| auditevents  | Предоставляет информацию о событиях аудита для текущего приложения.                                                                                                    |
|beans| Отображает полный список всех Spring-бинов в приложении.                                                                                                               |                                                                     |
|caches| 	Информация о кэше.                                                                                                                                                    |
|conditions| 	Показывает условия (Condition), которые были вычислены для классов конфигурации и автоконфигурации, и причины, по которым они соответствовали или не соответствовали. |
|configprops| 	Отображает список всех @ConfigurationProperties                                                                                                                       |
|env| 	Отображает свойства из ConfigurableEnvironment.                                                                                                                       |
|flyway| 	Показывает миграции баз данных Flyway, которые были применены.                                                                                                        |
|health| 	Показывает сведения о работоспособности приложения.                                                                                                                   |
|httptrace|	Отображает информацию трассировки HTTP (по умолчанию последние 100 HTTP запросов-ответов).|
|info|	Отображает дополнительную информацию о приложении.|
|integrationgraph|	Граф Spring Integration.|
|loggers|	Отображает и позволяет изменить конфигурацию логгеров в приложении.|
|liquibase	|Показывает примененные миграции базы данных Liquibase.|
|metrics|	Показывает информацию о метриках для текущего приложения.|
|mappings|	Отображает список всех путей @RequestMapping.|
|scheduledtasks	|Отображает запланированные задачи (scheduled tasks).|
|sessions|	Позволяет извлекать и удалять пользовательские сессии из хранилищ, поддерживаемых Spring Session. Недоступно при использовании Spring Session для реактивных веб-приложений.|
|shutdown	|Позволяет приложению корректно завершить работу.|
|threaddump	|Отображает информацию о потоках.|
