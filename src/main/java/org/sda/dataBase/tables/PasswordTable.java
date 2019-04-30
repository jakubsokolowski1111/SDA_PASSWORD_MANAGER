package org.sda.dataBase.tables;

public class PasswordTable {
    private String id;
    private String userName;
    private String title;
    private String descryption;
    private String email;
    private String url;
    private String password;
    private String rot;

    public PasswordTable(String id, String userName, String title,
                          String password, String rot) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.password = password;
        this.rot = rot;
    }

    public PasswordTable url(String url) {
        this.url = url;
        return this;
    }

    public PasswordTable email(String email) {
        this.email = email;
        return this;
    }

    public PasswordTable descryption(String descryption) {
        this.descryption = descryption;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRot() {
        return rot;
    }

    public void setRot(String rot) {
        this.rot = rot;
    }

    @Override
    public String toString() {
        return "Title: "+ title + "\nDescryption: " + descryption + "\nPassword: "+
                new String(password) + "\nROT: " + rot +"\n\n";
    }
}
