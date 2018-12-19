package cn.mth.mthuserprovider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {

        List<Parameter> pars = new ArrayList<Parameter>();

        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("code").description("是否登陆状态，200表示登陆状态，其他表示非登陆状态")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以


        ParameterBuilder ticketPar1 = new ParameterBuilder();
        ticketPar1.name("userId").description("用户ID")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可

        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar1.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("cn.mth.mthuserprovider.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);//把消息头添加
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("美特好购物平台接口文档")
                .description("restfun 风格接口，供程序，测试，前端人员使用。")
                //服务条款网址
                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                //.contact(new Contact("帅呆了", "url", "email"))
                .build();
    }

}
