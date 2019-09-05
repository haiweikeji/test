package hwkj.hwkj.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
@Configuration
public class SessionFactoryConfiguration {
    @Value("${mybatis_config_file_path}")
    private String mybatisConfigFilePath;
    @Value("${mapper_path}")
    private String mapper_path;
    @Value("${entity_package}")
    private String entityPackage;
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver =new PathMatchingResourcePatternResolver();
        String packageSearchPath =PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapper_path;
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactoryBean;
    }
}
