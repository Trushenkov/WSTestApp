package sample;

/**
 * Класс, в котором хранится информация для подключения к базе данных
 * <p>
 * Date: 23.01.2019 (среда)
 * Project name: TestApplication
 * Package name: sample
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
class Config {
    /**
     * MySQL
     */
    String dbHost = "localhost";
    String dbPort = "3306";
    String dbUser = "root";
    String dbPass = "12345";
    String dbName = "public";

    /**
     * PostgreSQL
     */
//    String dbHost = "localhost";
//    String dbPort = "5432";
//    String dbUser = "postgres";
//    String dbPass = "admin";
//    String dbName = "postgres";
}

