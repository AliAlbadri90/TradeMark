package ly.gov.eidc.archive.config;

import ly.gov.eidc.archive.service.util.JasperReportsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasperReportsConfig {

    @Bean
    public JasperReportsUtil jasperReportsUtil() {
        return new JasperReportsUtil("reports/");
    }
}
