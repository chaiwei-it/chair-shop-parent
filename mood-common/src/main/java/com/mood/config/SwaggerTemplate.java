package com.mood.config;


import com.fasterxml.classmate.TypeResolver;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableList;
import com.mood.model.swagger.SwaggerApiInfo;
import com.mood.model.swagger.SwaggerPaginationResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SwaggerTemplate {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerApiInfo apiInfo() {
        return SwaggerApiInfo.builder().title("MD-shop").version("v1").serviceUrl(null).statusList(ImmutableList.of()).build();
    }

    @Bean
    public Docket configure(SwaggerApiInfo info, TypeResolver typeResolver) {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("x-access-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mood"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .globalOperationParameters(pars)
                .globalResponseMessage(RequestMethod.OPTIONS, info.getStatusList())
                .apiInfo(new ApiInfo(info.getTitle(), "", info.getVersion(), info.getServiceUrl(), null, null, null))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(PageInfo.class, WildcardType.class),
                                typeResolver.resolve(SwaggerPaginationResponse.class, WildcardType.class)),
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(Collection.class, WildcardType.class),
                                typeResolver.resolve(List.class, WildcardType.class))
                )
                //.enableUrlTemplating(true)
                .forCodeGeneration(false);
    }


}