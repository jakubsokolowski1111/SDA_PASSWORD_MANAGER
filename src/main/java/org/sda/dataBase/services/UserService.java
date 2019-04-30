package org.sda.dataBase.services;

import org.sda.User;
import org.sda.dataBase.tables.PasswordTable;
import org.sda.dataBase.tables.UserTable;
import org.sda.exceptions.DatabaseIntegrityException;
import org.sda.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    void addPassword(String[] passwordData);

    List<PasswordTable> getAllPasswordsForUser(String username);

    void changePassword(char[] password, User user);

    void replaceUser(User user);

    boolean removeUser(User user) throws UserNotFoundException;

    void addUser(User user);

    List<UserTable> findAllUsers();

    UserTable findUserByName(String userName) throws UserNotFoundException;

    boolean isUserExistInDataBase(String userName);

    String findUserHashByName(String userName) throws UserNotFoundException;

    boolean findUserByEmail(User user);
}
