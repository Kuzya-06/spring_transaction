#

## Почему новая транзакция не откроется?
Из контроллера вызывается methodA()
![img_4.png](img_4.png)

Новая транзакция не будет открыта.
Сервис вызывает самого себя по своей ссылке.
![img_5.png](img_5.png)

Решить можно с помощью Self-injection
![img_6.png](img_6.png)
Решить можно через Setter.
```java
    private TransferService transferService;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    // Внедрение через сеттер (Spring автоматически вызовет его)
    @Autowired
    public void setTransferService(@Lazy TransferService transferService) {
        this.transferService = transferService;
    }
```
Решить можно через PostConstruct.

## Проверка
Посмотреть количество в терминале:
```text
Initiating transaction commit
```

## Узнаем какой прокси выполняется при иньекции

устанавливаем точку остановы  
![img_7.png](img_7.png)  
запускаем в debug  
![img_8.png](img_8.png)  
нажимаем Alt+F8 на поле beanDefinitionMap  
![img_9.png](img_9.png)  
Получаем наш объект TransferServiceImpl, не прокси.
```text
ru.kuz.spring_transaction.service.TransferServiceImpl@3986b9e9
```
Закроем окна.  
А теперь поставим точку остановы в `class TransferServiceImpl` на:
![img_10.png](img_10.png)  
и сделаем запрос из браузера. Мы остановились на точке.
Поместим наш transferService в отслеживаемые переменные (ПКМ, `+Add to Watches`).

![img_11.png](img_11.png)
И видим что у нас прокси `DynamicAopProxy`, потому что он более легковесный, если у нас есть интерфейс.
#### Когда делаем через инъекцию или через сеттер - у нас создается DynamicAopProxy. 
***
Если бы у нас не было интерфейса TransferService, то было бы так:

![img_12.png](img_12.png)  
И видим что у нас прокси `SpringCglib`  

