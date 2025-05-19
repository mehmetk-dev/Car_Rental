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
            SELECT * FROM vehicles WHERE id = ? AND is_available = true;
            """;

    public static final String RENTAL_SAVE = """
            INSERT INTO rentals(user_id,vehicle_id,total_price,start_date,end_date,rental_type) 
            VALUES(?,?,?,?,?,?);
            """;

    public static final String VEHICLE_UPDATE_AVAILABLE_FALSE = """
            UPDATE vehicles SET is_available = false WHERE id = ?;
            """;

    public static final String VEHICLE_UPDATE_AVAILABLE_TRUE = """
            UPDATE vehicles
            SET is_available = TRUE
            WHERE id = (SELECT vehicle_id FROM rentals WHERE id = ?);
            """;
    public static final String RENTAL_ALL_LIST_BY_USER_ID = """
            SELECT
            r.is_returned as is_returned,
            r.id as rental_id,
            v.category as vehicle_category,
            v.brand as brand,
            v.model as model,
            v.rental_rate as rental_rate,
            r.total_price as total_price,
            r.start_date as start_date,
            r.end_date as end_date
            FROM
            rentals r
            JOIN vehicles v ON v.id = r.vehicle_id
            where r.user_id = ?;
            """;

    public static final String RENTAL_ALL_LIST = """
            SELECT
            r.user_id as user_id,
            u.email as email,
            u.age as age,
            r.is_returned as is_returned,
            r.id as rental_id,
            v.category as vehicle_category,
            v.brand as brand,
            v.model as model,
            v.rental_rate as rental_rate,
            r.total_price as total_price,
            r.start_date as start_date,
            r.end_date as end_date
            FROM
            rentals r
            JOIN vehicles v ON v.id = r.vehicle_id
            JOIN users u ON u.id = r.user_id;
            """;

    public static final String RENTAL_RETURN_CAR = """
            UPDATE rentals set is_returned = true WHERE id = ?;
            """;
}
