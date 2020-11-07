package packages.app;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan({"packages.service","packages.controller"})
@EnableWebMvc
public class FormatterConfig implements WebMvcConfigurer {

}
