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