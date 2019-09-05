package hwkj.hwkj.utils;

public class ResultUtils {

    public static Result success(){
        Result result =new Result();
        result.setMessage("success");
        return result;
    }

    public static Result error(String error){
        Result result= new Result();
        result.setMessage(error);
        return result;
    }
}
