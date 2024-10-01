package ru.otus.services;

import java.util.List;
import java.util.Optional;
import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.security.RolePrincipal;
import org.eclipse.jetty.security.UserPrincipal;
import org.eclipse.jetty.util.security.Password;
import ru.otus.dao.UserDao;
import ru.otus.model.User;

public class InMemoryLoginServiceImpl extends AbstractLoginService {

    private final UserDao userDao;

    public InMemoryLoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected List<RolePrincipal> loadRoleInfo(UserPrincipal userPrincipal) {
        return List.of(new RolePrincipal("user"));
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        Optional<User> dbUser = userDao.findByLogin(login);
        return dbUser.map(u -> new UserPrincipal(u.getLogin(), new Password(u.getPassword())))
                .orElse(null);
    }
}
