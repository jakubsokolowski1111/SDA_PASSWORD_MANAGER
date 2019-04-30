package org.sda.comparators;

import org.sda.User;

public class UsernameComparator implements java.util.Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        int result = o1.getUserName().compareTo(o2.getUserName());
        if (result == 0){
            return new String(o1.getPassword()).compareTo(new String(o2.getPassword()));
        } else {
            return result;
        }
    }
}
