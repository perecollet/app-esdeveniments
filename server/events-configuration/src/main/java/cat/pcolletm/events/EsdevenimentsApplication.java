package cat.pcolletm.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
public class EsdevenimentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsdevenimentsApplication.class, args);
    }

}
