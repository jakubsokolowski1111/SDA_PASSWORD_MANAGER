package org.sda.authentication;

import org.sda.User;
import org.sda.authentication.hash.HashFunction;

public interface AuthService {
    boolean isAuthenticed(User user, HashFunction hashFunction);
}
