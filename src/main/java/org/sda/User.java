package org.sda;

import java.util.Arrays;
import java.util.Objects;

public class User implements Comparable<User>{
    private String userName;
    private char[] password;
    private Address address;
    private String email;
    private String academicTitle;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User(String userName, char[] password){
        this.userName = userName;
        this.password = password;
        this.academicTitle = "";
    }

    public User(String userName, char[] password,String academicTitle){
        this.academicTitle = academicTitle;
        this.userName = userName;
        this.password = password;
    }

    public User(){
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(String country,String city, String street, String houseNumber) {
        Address address1 = new Address(country,city,street,houseNumber);
        this.address = address1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password=" + Arrays.toString(password) +
                ", address=" + address +
                ", email=" + email +
                ", academic title=" +academicTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Arrays.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userName, address);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public int compareTo(User o) {
        int result = this.getUserName().compareTo(o.getUserName());
        if(result == 0){
            return new String(this.getPassword()).compareTo(new String(o.getPassword()));
        }else {
            return result;
        }
    }
}
