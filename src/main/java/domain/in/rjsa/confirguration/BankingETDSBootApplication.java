package domain.in.rjsa.confirguration;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.github.benmanes.caffeine.cache.Caffeine;

import domain.in.rjsa.interceptors.UserAuthorisationInterceptor;
import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class , ErrorMvcAutoConfiguration.class})
@EnableCaching
@EnableTransactionManagement
@EnableScheduling
@Controller
public class BankingETDSBootApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseTrailingSlashMatch(true);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/", ".jsp");
	}

	@Bean

	public FreeMarkerViewResolver freemarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setViewClass(FreeMarkerView.class);
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".html");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths("/WEB-INF/fmtemplates/");
		return configurer;
	}

	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("organizationDetails", "branch", "zone",
				"allAdminUsers", "StaticData", "login");
		cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(1000));
		return cacheManager;
	}

	@Bean

	public UserAuthorisationInterceptor userAuthorisationInterceptor() {
		return new UserAuthorisationInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userAuthorisationInterceptor());
	}

	@RequestMapping(value = { "/{path:[^\\.]*}", "/{path:[^\\.]*}/{path2:[^\\.-]*}",
			"/{path:[^\\.]*}/add/{path3:[^\\.-]*}", "/{path:[^\\.]*}/{path2:[^\\.-]*}/{path3:[^\\.-]*}",
			"/{path:[^\\.]*}/list/{path3:[^\\.-]*}/{params}",
			"/{path:[^\\.]*}/list/{path2:[^\\.-]*}/{path3:[^\\.-]*}/{params}",
			"/{path:[^\\.]*}/{path2:[^\\.-]*}/{path3:[^\\.-]*}/{path4:[^\\.-]*}",
			"/{path:[^\\.]*}/{path2:[^\\.-]*}/{path3:[^\\.-]*}/{path4:[^\\.-]*}/{path5:[^\\.-]*}",
			"/{path:[^\\.]*}/{path2:[^\\.-]*}/{path3:[^\\.-]*}/{path4:[^\\.-]*}/{params}",
			"/{path:[^\\.]*}/{path2:[^\\.-]*}/{path3:[^\\.-]*}/{path4:[^\\.-]*}/{path5:[^\\.-]*}/{path6:[^\\.-]*}" })
	public String redirect() {
		return "forward:/";
	}
}
