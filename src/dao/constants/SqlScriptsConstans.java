package dao.constants;

public class SqlScriptsConstans {

    public static final String USER_SAVE = """
            INSERT INTO users(age,email,password,user_type)
            VALUES(?,?,?,?)
            """;
    public static final String USER_FIND_BY_EMAIL = """
            SELECT * FROM users WHERE email = ?
            """;
}
