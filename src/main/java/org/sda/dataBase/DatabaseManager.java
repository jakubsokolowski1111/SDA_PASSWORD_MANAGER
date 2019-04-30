package org.sda.dataBase;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.sda.User;
import org.sda.authentication.AuthService;
import org.sda.authentication.hash.SHA256;
import org.sda.dataBase.tables.PasswordTable;
import org.sda.dataBase.tables.UserTable;

import org.sda.encryption.EncoderDecoder;
import org.sda.encryption.rots.ROT13;
import org.sda.encryption.rots.ROT18;
import org.sda.exceptions.UserNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public final class DatabaseManager {
    private static final Logger logger = Logger.getLogger(DatabaseManager.class);
    private static final DatabaseManager INSTANCE = new DatabaseManager();
    private static final Path USERS_CSV_FILE_PATH = Paths.get(
            "D:\\MojeProgramy\\ProgrammingII\\src\\main\\resources\\users.csv");
    private static final Path PASSWORDS_CSV_FILE_PATH = Paths.get(
            "D:\\MojeProgramy\\ProgrammingII\\src\\main\\resources\\passwords.csv");
    private static final String HEADERS = "username,email,country,hash";


    private DatabaseManager() {

    }

    //passwords
    void insertPassword(String[] passwordData) {
        passwordData[0] = setIdForPassword(getAllPasswords());

        if (passwordData[7].equals("ROT13")){
            EncoderDecoder encoder = new ROT13();
            passwordData[6] = new String(encoder.encrypt(passwordData[6].toCharArray()));
        }else if (passwordData[7].equals("ROT18")){
            EncoderDecoder encoder = new ROT18();
            passwordData[6] = new String(encoder.encrypt(passwordData[6].toCharArray()));
        }

        String fileName = "src/main/resources/passwords.csv";
        try (
                CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))
        ) {
            writer.writeNext(new String[]{passwordData[0],passwordData[1],passwordData[2],passwordData[3],passwordData[4],
                    passwordData[5],passwordData[6],passwordData[7]}, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String setIdForPassword(List<PasswordTable> allPasswords) {
        int id = 1;
        for (PasswordTable p: allPasswords) {
            if (Integer.parseInt(p.getId()) == id) id++;
            else break;
        }
        return String.valueOf(id);
    }

    List<PasswordTable> getAllPasswords() {
        List<PasswordTable> passwordDataList = new ArrayList<>();
        try (
                BufferedReader reader = Files.newBufferedReader(PASSWORDS_CSV_FILE_PATH);
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            List<String[]> allRecords = csvReader.readAll();
            for (String[] nextRecord : allRecords) {
                String id = nextRecord[0];
                String name = nextRecord[1];
                String title = nextRecord[2];
                String desc = nextRecord[3];
                String email = nextRecord[4];
                String url = nextRecord[5];
                String password = nextRecord[6];
                String rot = nextRecord[7];

                passwordDataList.add(new PasswordTable(id, name, title, password, rot)
                        .descryption(desc).email(email).url(url));


            }
        } catch (NoSuchFileException e) {
            logger.warn("File: " + USERS_CSV_FILE_PATH + " was not found." + e);
        } catch (IOException e) {
            logger.warn("Unexpected expection while reading file: " + USERS_CSV_FILE_PATH, e);
        }
        return passwordDataList;
    }

    List<PasswordTable> getAllPasswordsForUser(String userName) {
        List<PasswordTable> passwordDataList = new ArrayList<>();
        try (
                BufferedReader reader = Files.newBufferedReader(PASSWORDS_CSV_FILE_PATH);
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            List<String[]> allRecords = csvReader.readAll();
            for (String[] nextRecord : allRecords) {
                String id = nextRecord[0];
                String name = nextRecord[1];
                String title = nextRecord[2];
                String desc = nextRecord[3];
                String email = nextRecord[4];
                String url = nextRecord[5];
                String password = nextRecord[6];
                String rot = nextRecord[7];
                if (userName.equals(name)) {
                    passwordDataList.add(new PasswordTable(id, name, title, password, rot)
                            .descryption(desc).email(email).url(url));
                }

            }
        } catch (NoSuchFileException e) {
            logger.warn("File: " + USERS_CSV_FILE_PATH + " was not found." + e);
        } catch (IOException e) {
            logger.warn("Unexpected expection while reading file: " + USERS_CSV_FILE_PATH, e);
        }
        return passwordDataList;
    }


    //user
    public static DatabaseManager getINSTANCE() {
        return INSTANCE;
    }

    boolean insertUser(UserTable userTable) {
        String fileName = "src/main/resources/users.csv";
        try (
                CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))
        ) {
            writer.writeNext(new String[]{userTable.getUsername(), userTable.getEmail(), "none",
                    userTable.getHash()}, false);

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    List<UserTable> getAllUsers() {
        List<UserTable> userDataList = new ArrayList<>();
        try (
                BufferedReader reader = Files.newBufferedReader(USERS_CSV_FILE_PATH);
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            String[] nextRecord = csvReader.readNext();
            while (nextRecord != null) {
                String name = nextRecord[0];
                String email = nextRecord[1];
                String country = nextRecord[2];
                String hash = nextRecord[3];
                userDataList.add(new UserTable(name, email, country, hash));
                nextRecord = csvReader.readNext();

            }
        } catch (NoSuchFileException e) {
            logger.warn("File: " + USERS_CSV_FILE_PATH + " was not found." + e);
        } catch (IOException e) {
            logger.warn("Unexpected expection while reading file: " + USERS_CSV_FILE_PATH, e);
        }
        return userDataList;
    }

    UserTable findUserByName(String name) throws UserNotFoundException {
        List<UserTable> listOfUsers = getAllUsers();
        for (UserTable user : listOfUsers) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    boolean isUserExistInDataBase(String name) {
        boolean result = false;
        List<UserTable> usersList = getAllUsers();
        for (UserTable user : usersList) {
            if (user.getUsername().equals(name)) {
                result = true;
                break;
            }
        }
        return result;

    }


    public boolean isUserExistByEmail(User user) {
        List<UserTable> userTables = getAllUsers();
        for (UserTable u : userTables) {
            if (user.getEmail().equals(u.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public void removeUser(User user) throws UserNotFoundException {
        List<UserTable> list = getAllUsers();
        String userName = user.getUserName();
        int index = 0;
        for (UserTable u : list) {

            if (u.getUsername().equals(userName)) {
                break;
            }
            index++;
        }
        list.remove(index);
        cleanDatabase();
        insertUsersList(list);

    }

    boolean insertUsersList(List<UserTable> usersList) {
        String fileName = "src/main/resources/users.csv";
        for (UserTable u : usersList) {

            try (
                    CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))
            ) {
                writer.writeNext(new String[]{u.getUsername(), u.getEmail(), "none",
                        u.getHash()}, false);

            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }


    private void cleanDatabase() {
        String fileName = "src/main/resources/users.csv";
        try (
                CSVWriter writer = new CSVWriter(new FileWriter(fileName, false))
        ) {
            writer.flush();
            writer.writeNext(new String[]{HEADERS});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceUser(UserTable userTable) {
        List<UserTable> list = getAllUsers();
        int index = 0;
        for (UserTable user : list) {
            if (user.getUsername().equals(userTable.getUsername())) {
                break;
            }
            index++;
        }
        list.remove(index);
        list.add(index, userTable);
        cleanDatabase();
        insertUsersList(list);
    }

    public void changePassword(char[] password, User user) {
        UserTable userTable = null;
        try {
            userTable = findUserByName(user.getUserName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        userTable.setHash(new SHA256().hash(password));

        replaceUser(userTable);
    }
}


