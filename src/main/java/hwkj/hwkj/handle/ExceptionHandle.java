package hwkj.hwkj.handle;

import hwkj.hwkj.exception.GlobalException;
import hwkj.hwkj.utils.Result;
import hwkj.hwkj.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof GlobalException){
            GlobalException globalException =(GlobalException)e;
            return ResultUtils.error(globalException.getMessage());
        }else {
            logger.error("系统异常:",e);
            return ResultUtils.error("error");//未知异常
        }
    }
}
