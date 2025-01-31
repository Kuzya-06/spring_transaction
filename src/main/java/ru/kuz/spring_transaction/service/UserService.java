@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void createUser(UserDto userDto) {
        // Создаем пользователя
        User user = new User();
        user.setName(userDto.getName());
        userRepository.save(user);
        
        // Вложенный метод
        assignRole(user);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void assignRole(User user) {
        // Присваиваем роль пользователю
        // Если здесь произойдет ошибка, изменения будут откатаны только здесь
        Role role = new Role();
        role.setUserId(user.getId());
        role.setRoleName("USER");
        
        // Проблема на этом этапе вызовет откат только этой вложенной транзакции
        throw new RuntimeException("Ошибка при присвоении роли");
    }
}
