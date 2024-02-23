package com.great.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("com.great.controller")
public class SpringConfig 
{

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SpringResourceTemplateResolver templateResolver() {

		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

		resolver.setPrefix("/WEB-INF/view/");

		resolver.setSuffix(".html");

		resolver.setApplicationContext(applicationContext);

		resolver.setTemplateMode(TemplateMode.HTML);

		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {

		SpringTemplateEngine engine = new SpringTemplateEngine();

		engine.setTemplateResolver(templateResolver());

		engine.setEnableSpringELCompiler(true);

		return engine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {

		ThymeleafViewResolver vr = new ThymeleafViewResolver();

		vr.setTemplateEngine(templateEngine());

		return vr;
	}
}
