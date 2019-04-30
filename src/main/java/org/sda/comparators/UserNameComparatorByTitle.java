package org.sda.comparators;

import org.sda.User;

import java.util.Comparator;

public class UserNameComparatorByTitle implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        int result = o1.getAcademicTitle().compareTo(o2.getAcademicTitle());
        if (result == 0) {
            return o1.getUserName().compareTo(o2.getUserName());
        }else return result;
    }
}
