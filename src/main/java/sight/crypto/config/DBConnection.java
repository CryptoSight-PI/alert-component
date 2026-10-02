package sight.crypto.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBConnection {
    private final JdbcTemplate jdbcTemplate;
    private final BasicDataSource basicDataSource;

    public DBConnection() {
        BasicDataSource basicDataSource = new BasicDataSource();

        String url = "jdbc:mysql://" +
                System.getenv("DB_HOST") +
                ":" + System.getenv("DB_PORT") +
                "/" + System.getenv("DB_NAME");

        basicDataSource.setUrl(url);
        basicDataSource.setUsername(System.getenv("DB_NAME"));
        basicDataSource.setPassword(System.getenv("DB_PASSWORD"));

        this.basicDataSource = basicDataSource;
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}