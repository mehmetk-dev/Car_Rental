package dao.constants;

public class SqlScriptsConstans {

    public static final String USER_SAVE = """
            INSERT INTO users(age,email,password,user_type)
            VALUES(?,?,?,?)
            """;
    public static final String USER_FIND_BY_EMAIL = """
            SELECT * FROM users WHERE email = ?
            """;
    public static final String VEHICLE_SAVE = """
            INSERT INTO vehicles(brand,model,category,price,rental_rate) 
            VALUES(?,?,?,?,?);
            """;
    public static final String VEHICLE_LIST_ALL = """
            SELECT * FROM vehicles;
            """;
    public static final String VEHICLE_LIST_PAGE = """
            SELECT * FROM vehicles ORDER BY id LIMIT ? OFFSET ?
            """;
}
