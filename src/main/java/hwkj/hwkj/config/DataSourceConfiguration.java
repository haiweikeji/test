package hwkj.hwkj.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
@MapperScan("hwkj.hwkj.dao*")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource(){
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(jdbcDriver);
            dataSource.setJdbcUrl(jdbcUrl);
            dataSource.setUser(jdbcUsername);
            dataSource.setPassword(jdbcPassword);
            dataSource.setMaxPoolSize(20);//最大连接数
            dataSource.setMinPoolSize(5);//最小连接数
            dataSource.setInitialPoolSize(10);//default链接数
            dataSource.setMaxIdleTime(300);//连接的最大空闲时间，如果超过这个时间，某个数据库连接还没有被使用，则会断开掉这个连接,单位秒
            dataSource.setAcquireIncrement(5);//连接池在无空闲连接可用时一次性创建的新数据库连接数,default:3
            dataSource.setAcquireRetryAttempts(30);//连接池在获得新连接失败时重试的次数，如果小于等于0则无限重试直至连接获得成功
            dataSource.setAcquireRetryDelay(1000);//连接池在获得新连接时的间隔时间
            dataSource.setIdleConnectionTestPeriod(60);//每60秒检查所有连接池中的空闲连接Default:0
            dataSource.setAutoCommitOnClose(false); // 关闭连接后不自动提交
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
