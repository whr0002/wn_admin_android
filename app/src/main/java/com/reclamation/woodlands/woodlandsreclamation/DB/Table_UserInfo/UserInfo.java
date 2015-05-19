package com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo;

/**
 * Created by Jimmy on 5/12/2015.
 */
public class UserInfo {
    public String username;
    public String password;
    public String role;

    public static final String TABLE_USERINFO = "UserInfo";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_ROLE = "Role";
    public static final String USERINFO_CREATE = "CREATE TABLE " + TABLE_USERINFO + " ( "
            + COLUMN_USERNAME + " text primary key, "
            + COLUMN_PASSWORD + " text not null, "
            + COLUMN_ROLE + " text "
            + " );";

    public UserInfo(String email, String password, String role){
        this.username = email;
        this.password = password;
        this.role = role;
    }
}
