 package top.lemcoo.exam.security;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.cors.CorsConfiguration;
 import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 import org.springframework.web.filter.CorsFilter;
 import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 /**
  * 【解决跨域】
  *
  * @Author: zhaowx
  * @Date: 2021/7/13 20:24
  */
 @Configuration
 public class CorsConfig implements WebMvcConfigurer {

     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOrigins("*")
                 //暴露header中的其他属性给客户端应用程序
                 //如果不设置这个属性前端无法通过response header获取到Authorization也就是token
                 .exposedHeaders("Authorization")
                 .allowCredentials(true)
                 .allowedMethods("GET", "POST", "DELETE", "PUT")
                 .maxAge(3600);
     }
 }
