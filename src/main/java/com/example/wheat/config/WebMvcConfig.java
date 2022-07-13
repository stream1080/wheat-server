package com.example.wheat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .allowedOrigins("*");
            }
        };
    }

    /**
     * 配置静态资源地址
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * 重新配置静态资源目录
         */
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/");

        /**
         * 重新配置Swagger2目录
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * 重新配置Swagger2目录
         */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        /**
         * 静态图片目录
         */
        //http://localhost:8080/sockjs-node/info?t=1623153630120'
        registry.addResourceHandler("/sockjs-node/**")
                .addResourceLocations("classpath:/static/sockjs-node/");

    }
}






//        String dir = System.getProperty("user.dir"); //当前项目的绝对路径
//        String separator = System.getProperty("file.separator");//文件分隔符
//        //获取图片
//        //http://localhost:8080/sockjs-node/info?t=1623153630120'
//        registry.addResourceHandler("/sockjs-node/**").addResourceLocations(
//                "file:" + dir + separator + "sockjs-node" + separator
//        );
//        //歌单图片地址
//        registry.addResourceHandler("/img/songListPic/**").addResourceLocations(
//                "file:" + dir + separator + "img" + separator + "songListPic" + separator
//        );
//        //歌曲图片地址
//        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
//                "file:" + dir + separator + "img" + separator + "songPic" + separator
//        );
//        //歌曲地址
//        registry.addResourceHandler("/song/**").addResourceLocations(
//                "file:" + dir + separator + "song" + separator
//        );
//        //前端用户头像地址
//        registry.addResourceHandler("/avatorImages/**").addResourceLocations(
//                "file:" + dir + separator + "avatorImages" + separator
//        );
//        //用户头像默认地址
//        registry.addResourceHandler("/img/**").addResourceLocations(
//                "file:" + dir + separator + "img" + separator
//        );
//    }
//}
