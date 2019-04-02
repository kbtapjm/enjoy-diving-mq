package kr.co.pjm.diving.mq;

import java.nio.charset.Charset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.Compression;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.co.pjm.diving.mq.model.Email;

@SpringBootApplication(scanBasePackages = {"kr.co.pjm.diving.mq"})
@EnableScheduling
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    
    /*// Launch the application
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

    JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

    // Send a message with a POJO - the template reuse the message converter
    System.out.println("Sending an email message.");
    jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));*/
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    builder.sources(Application.class);
    return builder;
  }

  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
    return (ConfigurableEmbeddedServletContainer container) -> {
      if (container instanceof TomcatEmbeddedServletContainerFactory) {
        Compression compression = new Compression();
        compression.setEnabled(true);
        compression.setMinResponseSize(2048);
        container.setCompression(compression);
      }
    };
  }

  @Bean
  public HttpMessageConverter<String> responseBodyConverter() {
    return new StringHttpMessageConverter(Charset.forName("UTF-8"));
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setForceEncoding(true);
    characterEncodingFilter.setEncoding("UTF-8");

    registrationBean.setFilter(characterEncodingFilter);

    return registrationBean;
  }

  @Override
  public void run(String... args) throws Exception {
    // CommandLineRunner
  }

}
