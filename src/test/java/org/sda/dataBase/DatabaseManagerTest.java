package org.sda.dataBase;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseManagerTest {
    @Test
    public void singletonTest(){
        DatabaseManager databaseManager = DatabaseManager.getINSTANCE();
        DatabaseManager databaseManager2 = DatabaseManager.getINSTANCE();
        assertSame(databaseManager,databaseManager2);
    }

}