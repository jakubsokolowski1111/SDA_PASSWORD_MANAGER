package org.sda.authentication;

import org.sda.User;
import org.sda.authentication.hash.HashFunction;
import org.sda.authentication.hash.SHA256;
import org.sda.dataBase.UserServiceImpl;
import org.sda.dataBase.tables.UserTable;
import org.sda.exceptions.UserNotFoundException;

import java.util.logging.Logger;

public class AuthServiceImpl implements AuthService {
    private UserServiceImpl userDAO = new UserServiceImpl();

    @Override
    public boolean isAuthenticed(final User user, HashFunction hashFunction) {


        String userHashedPassword = new SHA256().hash(user.getPassword());

        System.out.println("userHashedPassword = " + userHashedPassword);
        UserTable userTable = null;
        userTable = userDAO.findUserByName(user.getUserName());
        return userHashedPassword.equalsIgnoreCase(userTable.getHash());
    }


}
