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
            SELECT * FROM vehicles
            ORDER BY is_available desc;
            """;
    public static final String VEHICLE_LIST_PAGE = """
            SELECT * FROM vehicles ORDER BY id LIMIT ? OFFSET ?
            """;
    public static final String VEHICLE_DELETE_BY_ID = """
            DELETE FROM vehicles WHERE id = ?;
            """;
    public static final String VEHICLE_FILTER_BY_CATEGORY = """
            SELECT * FROM vehicles WHERE category = ?;
            """;
    public static final String VEHICLE_FIND_BY_ID = """
            SELECT * FROM vehicles WHERE id = ? AND is_available = false;
            """;
    public static final String RENTAL_SAVE = """
            INSERT INTO rentals(user_id,vehicle_id,total_price,start_date,end_date,rental_type) 
            VALUES(?,?,?,?,?,?);
            """;
    public static final String VEHICLE_UPDATE_AVAILABLE = """
            UPDATE vehicles SET is_available = false WHERE id = ?;
            """;
}
