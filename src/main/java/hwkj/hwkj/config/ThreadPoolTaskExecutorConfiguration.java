package hwkj.hwkj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ComponentScan("hwkj.hwkj.service")
@EnableAsync
public class ThreadPoolTaskExecutorConfiguration implements AsyncConfigurer {

    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//配置核心线程数
        executor.setMaxPoolSize(15);//配置最大线程数
        executor.setKeepAliveSeconds(5);//线程池维护线程所允许的空闲时间
        executor.setQueueCapacity(25);//配置队列大小
        executor.setThreadNamePrefix("async-service-");//配置线程池中的线程的名称前缀
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();//执行初始化
        return executor;
    }

}
