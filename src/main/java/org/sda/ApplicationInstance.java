package org.sda;

import org.apache.log4j.Logger;
import org.sda.authentication.AuthService;
import org.sda.authentication.AuthServiceImpl;
import org.sda.authentication.hash.SHA256;
import org.sda.dataBase.DatabaseManager;
import org.sda.dataBase.UserServiceImpl;
import org.sda.dataBase.services.UserService;
import org.sda.dataBase.tables.PasswordTable;
import org.sda.dataBase.tables.UserTable;
import org.sda.encryption.EncoderDecoder;
import org.sda.encryption.rots.ROT13;
import org.sda.encryption.rots.ROT18;
import org.sda.exceptions.UserNotFoundException;
import org.sda.validators.Validators;

import java.io.Console;
import java.util.*;


public class ApplicationInstance {

    private static final AuthService authService = new AuthServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    //dodawanie logera,raz na klase:
    private static final Logger logger = Logger.getLogger(ApplicationInstance.class);


    private Scanner scanner;
    private Console console;


    public ApplicationInstance() {
        scanner = new Scanner(System.in);

        console = System.console();
    }

    public void run() {

        showMenu();
    }

    private void showInsideMenu(User user) {
        logger.info("User login: " + user);
        System.out.println("========================================");
        System.out.println("|                 Menu                 |");
        System.out.println("========================================");
        /*System.out.println("|  1. Add password.                    |");*/
        /*System.out.println("|  2. Show list of passwords.          |");
        System.out.println("|  3. Show list of decrypted passwords.|");*/
        System.out.println("|  1. Password options.                |");
        System.out.println("|  2. Show user address.               |");
        System.out.println("|  3. Change password.                 |");
        System.out.println("|  4. Delete your account.             |");
        System.out.println("|  5. Log out.                         |");
        System.out.println("=======================================");

        int digit = scanNextInt();

        switch (digit) {
            case 1:
                passwordsOptionsView(user);
                break;
            case 2:
                System.out.println(user.getAddress());
                showInsideMenu(user);
                break;
            case 3:
                changePassword(user);
                break;
            case 4:
                boolean arePasswordsTheSame = passwordAuthorization(user);
                if (arePasswordsTheSame) {
                    try {
                        userService.removeUser(user);
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Wrong password.");
                    showInsideMenu(user);
                }
                break;
            case 5:
                System.out.println("You are log out.");
                logger.info("User logout: " + user);
                showMenu();
                break;

        }
    }

    private void passwordsOptionsView(User user) {

        System.out.println("========================================");
        System.out.println("|                 Menu                 |");
        System.out.println("========================================");
        System.out.println("|  1. Add password.                    |");
        System.out.println("|  2. Show list of passwords.          |");
        System.out.println("|  3. Show list of decrypted passwords.|");
        System.out.println("|  4. Back.                            |");
        System.out.println("========================================");
        int digit = scanner.nextInt();
        scanner.nextLine();

        switch (digit) {
            case 1:
                System.out.println("dodajemy haslo");
                addPassword(user);
            case 2:
                //printListOfPasswords(list);
                System.out.println("Your passwords: ");
                System.out.println(userService.getAllPasswordsForUser(user.getUserName()));
                showInsideMenu(user);
                break;
            case 3:
                System.out.println("Your decrypted passwords: ");
                showDecryptedPasswords(user);
            case 4:
                showInsideMenu(user);
                break;

        }

    }

    private void showDecryptedPasswords(User user) {
        List<PasswordTable> list = userService.getAllPasswordsForUser(user.getUserName());
        for (PasswordTable p: list) {
            if (p.getRot().equals("ROT18")){
                EncoderDecoder decoder = new ROT18();
                p.setPassword(new String(decoder.decrypt(p.getPassword().toCharArray())));
            }
            if (p.getRot().equals("ROT13")){
                EncoderDecoder decoder = new ROT13();
                p.setPassword(new String(decoder.decrypt(p.getPassword().toCharArray())));
            }
        }
        System.out.println(list);
    }

    private void addPassword(User user) {
        String[] passwordData = new String[8];
        int rot = 0;
        for (int i = 0; i < passwordData.length; i++) {
            switch (i) {
                case 2:
                    System.out.println("Enter title of password: ");
                    break;
                case 3:
                    System.out.println("Enter descryption: ");
                    break;
                case 4:
                    System.out.println("Enter email: ");
                    break;
                case 5:
                    System.out.println("Enter URL: ");
                    break;
                case 6:
                    System.out.println("Enter password: ");
                    break;
                case 7:
                    System.out.println("Choose rot: ");
                    System.out.println("1. ROT13");
                    System.out.println("2. ROT18");
                    rot = scanner.nextInt();
                    break;

            }

            if (i == 1) passwordData[i] = user.getUserName();
            if (i >= 2 && i <= 6) {
                passwordData[i] = scanner.nextLine();
            }
            if (rot == 1){
                passwordData[i] = "ROT13";
            }
            if (rot == 2){
                passwordData[i] = "ROT18";
            }
        }
        userService.addPassword(passwordData);

    }

    private void changePassword(User user) {

        if (passwordAuthorization(user)) {
            char[] password = enterPassword();
            char[] password2 = enterPassword();

            if (Arrays.equals(password, password2)) {
                userService.changePassword(password, user);
                user.setPassword(password);
                System.out.println("Password changed.");
                showInsideMenu(user);
            } else {
                System.out.println("Passwords have to be equals.");
                showInsideMenu(user);
            }

        }
    }


    private boolean passwordAuthorization(User user) {
        boolean arePasswordsTheSame = false;

        System.out.println("Enter password: ");
        char[] password = console.readPassword();
        System.out.println("Repeat password: ");
        char[] reapetedPassword = console.readPassword();
        if (Arrays.equals(password, reapetedPassword)) {

            System.out.println("Passwords are the same.");
            arePasswordsTheSame = true;
        }
        String userPassword = new SHA256().hash(password);
        try {
            arePasswordsTheSame = userService.findUserHashByName(user.getUserName()).equals(userPassword);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        if (!arePasswordsTheSame) return false;

        return arePasswordsTheSame;
    }


    private int scanNextInt() {
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    private void showMenu() {
        System.out.println("======================================");
        System.out.println("|              Menu                  |");
        System.out.println("======================================");
        System.out.println("|     1. Login.                      |");
        System.out.println("|     2. Register.                   |");
        System.out.println("|     3. Show list all users.        |");
        System.out.println("|     4. Find user.                  |");
        System.out.println("|     5. Exit.                       |");
        System.out.println("======================================");

        int digit = scanNextInt();

        switch (digit) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                printListOfUsers();
                break;
            case 4:
                findUser();

                break;
            case 5:
                System.out.println("wychodzimy");
                break;

        }

    }

    private void printListOfUsers() {

        for (UserTable u : userService.findAllUsers()) {
            System.out.println(u.getUsername());
        }
        showMenu();
    }

    private void findUser() {
        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();
        boolean userFound = false;
        for (UserTable u : userService.findAllUsers()) {
            if (u.getUsername().equals(userName)) {
                System.out.println(u);
                userFound = true;
            }
        }

        if (!userFound) {
            System.out.println("There is no user with this name.");
            showMenu();
        }


    }

    private void register() {
        User user = new User();
        String userName;
        boolean loginIsOk;
        do {
            System.out.println("Enter user name: ");

            userName = scanner.nextLine();
            loginIsOk = Validators.checkUsername(userName);

            if (!loginIsOk) {
                System.out.println("LOGIN have to contains only letters and digits.\n" +
                        "Can't starts with digit.\n" +
                        "Have to be longer than 5 and shorter or equal 15\n");
            }
        } while (!loginIsOk);

        user.setUserName(userName);


        char[] password = enterPassword();

        System.out.println("Password: " + new String(password));
        user.setPassword(password);
        //System.out.println("Password: " + new String(user.getPassword()));


        String email;
        boolean emailIsOk;
        do {
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            emailIsOk = Validators.checkEmail(email);
            if (!emailIsOk) {
                System.out.println("EMAIL have to contains only letters, digits and special characters like:\n" +
                        "'.', ',', '-', '@'.\n" +
                        "Have to contains exactly one '@' character preceded at least 3 characters and after '@' " +
                        "have to be also at least 3 characters.\n" +
                        "Length of EMAIL have to be longer than 6 and shorter or equal than 20.\n" +
                        "Special characters can't be next to each other.");
            }
        } while (!emailIsOk);


        user.setEmail(email);


        if (!isUserAlreadyExistByName(user)) {
            //users.add(user);
            if (isUserAlreadyExistByMail(user)) {
                userService.addUser(user);
                System.out.println("Registered succesfull!");
            } else {
                System.out.println("Email already in use");
            }

        } else {
            System.out.println("Login is already in use.");
        }
        showMenu();
    }

    private char[] enterPassword() {
        char[] password;
        boolean passwordIsOk;
        do {
            password = console.readPassword("Enter password: ");
            passwordIsOk = Validators.checkPassword(password);

            if (!passwordIsOk) {
                System.out.println("PASSWORD have to contains only letters and digits and some special characters like: \n" +
                        "' ', '@', '!', '/', '^'.\n" +
                        "Should contains at least two big letters.\n" +
                        "Should contains at least two digits.\n" +
                        "Have to be longer or equal 7 and shorter od equal 15\n");
            }
        } while (!passwordIsOk);

        return password;
    }

    private boolean isUserAlreadyExistByMail(User user) {
        return userService.findUserByEmail(user);
    }


    private void login() {


        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();

        char[] password = console.readPassword("Enter password: ");
        System.out.println("Password: " + new String(password));
        User user = new User(userName, password);
        boolean isUserExist = isUserAlreadyExistByName(user);

        if (isUserExist) {
            if (authService.isAuthenticed(user, new SHA256())) {
                user = returnUserByName(userName);
                showInsideMenu(user);
            } else {
                System.out.println("Wrong password");
                showMenu();
            }
        } else {
            System.out.println("You have to register first.");
            showMenu();
        }


        //throw new IllegalArgumentException("To much arguments");

        //System.out.println("User name: " + args[0] + "\nPassword: " + args[1]);

    }


    private User returnUserByName(String userName) {
        User user = null;
        for (UserTable u : userService.findAllUsers()) {
            if (userName.equals(u.getUsername())) {
                user = new User(u.getUsername(), u.getEmail());
                break;
            }
        }
        return user;
    }


    private boolean isUserAlreadyExistByName(User user) {
        boolean result = userService.isUserExistInDataBase(user.getUserName());
        return result;
    }

    private void printListOfPasswords(List<String> list) {
        for (String e : list) {
            System.out.println(e);
        }
    }


}
/*
 */
/*Scanner scanner = new Scanner(System.in);
        boolean isClosed = true;
        while (isClosed) {
            System.out.println("This is menu: \n1. Do something1\n2.Exit");
            int digit = scanner.nextInt();
            switch (digit) {
                case 1:
                    //doSomething();
                    break;
                case 2:
                    isClosed = false;
                    break;
                default:
                    System.out.println("Wrong input,try again");
                    break;
            }
        }*/


/*int digit2 = scanner.nextInt();
        System.out.println("Digit2: " + digit2);*/

        /*try{
            int digit = scanner.nextInt();
            System.out.println("Digit: " + digit);
        } catch (InputMismatchException e){
            System.out.println("Wrong data format - expected number.");
            scanner.next();//program przechodzi dalej
        }*/

        /*Console console = System.console();
        String userName2 = console.readLine();
        System.out.println("Hello: " + userName2);
        char [] password = console.readPassword("Enter password");
        System.out.println("Password: " + new String(password));*/



        /*for (String s : args) {
            System.out.println("Args + " + s);
        }*/



        /*User user = new User();
        if (args.length == 0) {


            System.out.println("Enter user name: ");
            user.setUserName(scanner.nextLine());


            Console console = System.console();
            char[] password = console.readPassword("Enter password: ");
            System.out.println("Password: " + new String(password));
            user.setPassword(password);
            System.out.println("Password: " + new String(user.getPassword()));
        } else if (args.length > 2 || args.length == 1) {
            throw new IllegalArgumentException("To much arguments");
        } else {
            System.out.println("User name: " + args[0] + "\nPassword: " + args[1]);
        }*/






        /*if (args.length == 2 || (user.getUserName().length() > 0 && user.getPassword().length > 0)) {
            boolean isClosed = true;

            while (isClosed) {

                System.out.println("======================================");
                System.out.println("|              Menu                  |");
                System.out.println("======================================");
                System.out.println("|  1. Show list of passwords         |");
                System.out.println("|  2. Show user address              |");
                System.out.println("|  3. Log out                        |");
                System.out.println("======================================");
                Scanner scanner = new Scanner(System.in);
                int digit = scanner.nextInt();
                switch (digit) {
                    case 1:
                        printListOfPasswords(list);
                        isClosed = false;
                        break;
                    case 2:
                        System.out.println(user.getAddress());
                        isClosed = false;
                        break;
                    case 3:
                        System.out.println("You are log out.");
                        isClosed = false;
                        showViewThree();
                        break;
                }
            }
        }*/