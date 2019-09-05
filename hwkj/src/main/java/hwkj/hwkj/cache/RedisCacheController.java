package hwkj.hwkj.cache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class RedisCacheController {
    private RedisCache redisCache;

    @RequestMapping(value = "/redisCacheTest",method = RequestMethod.POST)
    @ResponseBody
    public void RedisCacheTest(){
        redisCache.set("name","shenquan");
    }
}
