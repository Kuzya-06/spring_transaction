# CGLIB или Dynamic Proxy
## Как проверить CGLIB или Dynamic Proxy у нас
* Для классов final означает, что класс не сможет иметь подклассов, то есть запрещено наследование.

Сделаем сервисный класс final
```java
@Service
public final class TransferServiceImpl
```
### CGLIB Proxy

```mvn clean install```, запустим приложение.

Получим следующий текст в терминале:
```text
Could not generate CGLIB subclass of class ru.kuz.spring_transaction.service.TransferServiceImpl: Common causes of this problem include using a final class or a non-visible class
Не удалось создать подкласс CGLIB класса ru.kuz.spring_transaction.service.TransferServiceImpl: частые причины этой проблемы включают использование конечного класса или невидимого класса.
```

### Dynamic Proxy

добавим строчку в application.properties
```properties
spring.aop.proxy-target-class=false
```
```mvn clean install```, запустим приложение и отправим запрос.

У нас всё запустилось, так как прокси создан на `интерфейс`

