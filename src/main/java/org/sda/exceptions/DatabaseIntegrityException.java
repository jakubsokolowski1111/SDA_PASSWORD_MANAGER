package org.sda.exceptions;

public class DatabaseIntegrityException extends Exception {
    public DatabaseIntegrityException(){
        super();
        System.out.println("Dany klucz istnieje juz w bazie danych.");
    }
}
