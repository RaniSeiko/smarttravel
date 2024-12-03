package cn.edu.zust.se.smarttravel.config;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/"); // 将静态资源文件夹映射为根路径下的访问路径
    }
}
