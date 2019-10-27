package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;


/**
 * 该类是一个配置类，她的作用和bean.xml是一样的
 * spring中的新注解
 * @Configuration:指定当前类是一个配置类
 *       当配置类作为AnnotationConfigApplicationContext对象创建参数时，可以不写该注解
 * @ComponentScan:用于通过注解指定spring在创建容器时要扫描的包
 *      属性：value:它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *                  使用此注解就等同于在xml中配置了:
 *                  <context:component-scan base-package="com.jxau"></context:component-scan>
 * @Bean:用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性：name:用于指定bean的id。不写时，默认值是当前方法的名称
 *
 *
 *      细节：使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象。
 *            查找的方式和Autowired注解的方式是一样的
 * @Import:用于导入其他的配置类
 *      属性：value:用于指定其他配置类的字节码
 *             使用Import的注解之后，有Import注解的类就是父配置类，而导入的都是子配置类
 * @PropertySource:用于指定properties文件的位置
 *      属性：指定文件的名称和路径
 *              关键字：classpath,表示类路径下
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.jxau"} )
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
