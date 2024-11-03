package ru.dmitriev.NauJavaSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.dmitriev.NauJavaSpring.entity.User;
import ru.dmitriev.NauJavaSpring.repository.RoleRepository;
import ru.dmitriev.NauJavaSpring.repository.UserRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
                           PlatformTransactionManager transactionManager)
    {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }
    @Override
    public void deleteRole(String roleTitle)
    {
        TransactionStatus status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try
        {
            // удалить пользователей с данной ролью
            List<User> users = userRepository.findByRole(roleTitle);
            for (User user : users)
            {
                userRepository.delete(user);
            }
            // удалить роль
            roleRepository.deleteByTitle(roleTitle);
            // фиксация транзакции
            transactionManager.commit(status);
        }
        catch (DataAccessException ex)
        {
            // откатить транзакцию в случае ошибки
            transactionManager.rollback(status);
            throw ex;
        }
    }
}