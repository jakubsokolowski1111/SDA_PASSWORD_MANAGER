package org.sda.dataBase;

import org.sda.User;
import org.sda.dataBase.services.UserService;
import org.sda.dataBase.tables.PasswordTable;
import org.sda.dataBase.tables.UserTable;
import org.sda.exceptions.DatabaseIntegrityException;
import org.sda.exceptions.UserNotFoundException;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void addPassword(String[] passwordData) {
        DatabaseManager.getINSTANCE().insertPassword(passwordData);
    }

    @Override
    public List<PasswordTable> getAllPasswordsForUser(String username) {
        return DatabaseManager.getINSTANCE().getAllPasswordsForUser(username);
    }

    @Override
    public void changePassword(char[] password, User user) {
        DatabaseManager.getINSTANCE().changePassword(password,user);
    }

    @Override
    public void replaceUser(User user) {
        UserTable userTable = new UserTable(user);
        DatabaseManager.getINSTANCE().replaceUser(userTable);
    }

    @Override
    public boolean removeUser(User user) throws UserNotFoundException {

        DatabaseManager.getINSTANCE().removeUser(user);
        return true;
    }

    @Override
    public void addUser(User user) {
        UserTable userTable = new UserTable(user);
        DatabaseManager.getINSTANCE().insertUser(userTable);
    }

    @Override
    public List<UserTable> findAllUsers() {
        return DatabaseManager.getINSTANCE().getAllUsers();
    }

    @Override
    public UserTable findUserByName(String userName)  {
        UserTable userTable = null;
        try {
            userTable = DatabaseManager.getINSTANCE().findUserByName(userName);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return userTable;
    }

    @Override
    public boolean isUserExistInDataBase(String userName) {
        return DatabaseManager.getINSTANCE().isUserExistInDataBase(userName);
    }

    @Override
    public String findUserHashByName(String userName) throws UserNotFoundException {
        return DatabaseManager.getINSTANCE().findUserByName(userName).getHash();
    }

    @Override
    public boolean findUserByEmail(User user) {
        return DatabaseManager.getINSTANCE().isUserExistByEmail(user);
    }
}
