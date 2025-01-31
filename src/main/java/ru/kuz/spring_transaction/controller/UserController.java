package ru.kuz.spring_transaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public boolean transfer(@RequestBody TransferRestModel transferRestModel) {
        userService.createUser(transferRestModel);

        return true;
    }
}
