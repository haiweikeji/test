package hwkj.hwkj;

import com.graphbuilder.struc.Stack;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MD5 {

    @Test
    public void encryption(){
        String password="123456";
        String newPassword=DigestUtils.md5Hex(password);
        System.out.println(newPassword);
        if("e10adc3949ba59abbe56e057f20f883e".equals(newPassword)){
            System.out.println("login");
        }
    }

    @Test
    public void test3(){
        long startTime=System.currentTimeMillis();
        int i=5,sum=0;
        while (i<=100000){
            if(i%5==0){
                sum=sum+i;
            }
            i++;
        }
        long endTime=System.currentTimeMillis();
        System.out.println((endTime-startTime));
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(sum);
    }

    @Test
    public void test4(){
        String string="HelloWorld";
        char [] c=string.toCharArray();
        char chars[]=new char[string.length()];
        for (int i=c.length-1;i>=0;i--){
            chars[string.length()-i-1]=c[i];
            //System.out.println(c[i]);
        }
        //System.out.println(new String(chars));
        System.out.println(String.valueOf(chars));
    }

    @Test
    public void test5(){
        String string="HelloWorld";
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer=stringBuffer.append(string);
        System.out.println(stringBuffer.reverse().toString());
    }

    @Test
    public void test6(){
        String string="HelloWorld";
        Stack stack=new Stack();
        char [] chars=string.toCharArray();
        for (Character ch:chars
             ) {
            stack.push(ch);
        }
        int length=string.length();
        for (int i=0;i<length;i++){
            chars[i]=(char)stack.pop();
        }
        System.out.println(new String(chars));
    }

    @Test
    public void test7(){
        int[] a = {3,5,9,7,2};
        Arrays.sort(a); // 排序
        for (int array:a){
            System.out.println(array);
        }
        int index=Arrays.binarySearch(a,2);//二分查找
        System.out.println(index);
    }

    @Test
    public void test8(){
        int[] a = {3,5,9,7,2};
        int[] newa = Arrays.copyOf(a, 2);
        int[] newa2 = Arrays.copyOf(a, 7);  //复制长度大于原数组的长度
        for (int item : newa){
            System.out.println(item + " ");
        }
        for (int item : newa2){
            System.out.println(item + " ");
        }
    }

    @Test
    public void Test9(){
        int[] a = {3,5,9,7,2};
        int[] newa = Arrays.copyOfRange(a, 1, 3);
        int[] newa2 = Arrays.copyOfRange(a, 1, 8);  //复制长度大于原数组的长度
        for (int item : newa){
            System.out.println(item + " ");
        }
        for (int item : newa2){
            System.out.println(item + " ");
        }
    }

    @Test
    public void test10(){
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);
        players.forEach((player) ->  System.out.println(player + ";"));
    }

    @Test
    public void test11(){
        //Integer[] numbers = {7, 7, 8, 9, 10, 8, 8, 9, 6, 5, 4};
        List<Integer> list=new ArrayList();
        for (int i=0;i<10;i++){
            list.add(i);
        }
        Integer numbers[]=list.toArray(new Integer[list.size()]);
        for (int i=0;i<10;i++){
            System.out.println(numbers[i]);
        }
        Set set=new HashSet(list);
        Iterator iterator=set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 遍历List3种方式
     */
    @Test
    public void test12(){
        List<String> list=new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        for (String string :list){
            System.out.println(string);
        }
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
           System.out.println(iterator.next());
        }
    }

    /**
     * 遍历Set2种方式
     */
    @Test
    public void test13(){
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        Iterator iterator=set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for (String string:set){
            System.out.println(string);
        }
    }

    /**
     * 遍历Map的4种方式
     */
    @Test
    public void test14(){
        Map<String, String> map = new HashMap<>();
        map.put("姓名", "张三");
        map.put("性别", "男");
        for (String key:map.keySet()){
            System.out.println(map.get(key));
        }
        Iterator<Map.Entry<String,String>> iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            System.out.println(entry.getKey() + entry.getValue());
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
        for (String value : map.values()) {
            System.out.println(value);
        }
    }

    @Test
    public void test15(){
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<10000000;i++){
            list.add(i);
        }
        long startTime=System.currentTimeMillis();
        /*for (int i=0,length=list.size();i<length;i++){
            //System.out.println(list.get(i));
            list.get(i);
        }*/
        for (Integer item :list){
            //System.out.println(item);
            //Integer integer=item;
        }
        /*Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            //System.out.println(iterator.next());
            iterator.next();
        }*/
        System.out.println(System.currentTimeMillis()-startTime);
    }

    @Test
    public void test16(){
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<1000000;i++){
            set.add(i);
        }
        long startTime=System.currentTimeMillis();
        Iterator iterator=set.iterator();
        while (iterator.hasNext()){
            //System.out.println(iterator.next());
            iterator.next();
        }
        /*for (Integer item:set){
            //System.out.println(item);
        }*/
        System.out.println(System.currentTimeMillis()-startTime);
    }

    @Test
    public void test17(){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<1000000;i++){
            map.put(i,i);
            //map.putAll(map);
        }
        System.out.println(map.size());
        long startTime=System.currentTimeMillis();
        /*for (Integer key :map.keySet()){
            map.get(key);
        }*/
        /*Iterator iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            iterator.next();
        }*/
        /*for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            entry.getKey();
            entry.getValue();
        }*/
        for(Integer value:map.values()){
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }

    @Test
    public void test18(){
        double d1=15.169;
        double d2=14.01;
        System.out.println(d1-d2);
    }

    @Test
    public void test19(){
        float f1=15.1f;
        float f2=0.00001f;
        System.out.println(f1-f2);
    }

    @Test
    public void test20(){
        double d=5.02;
        int a=2;
        System.out.println(a-d);
    }

    @Test
    public void test21(){
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(new File("G:\\file.txt"));
            for(int i=0;i<5000;i++){
                //char c=(char)i;
                fileOutputStream.write(i);
            }
            //fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream!=null){
                try{
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test22(){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream(new File("G:\\file2.txt"));
            //int read=fileInputStream.read(new byte[1024]);
            int read;
            byte b[]=new byte[1024];
            while (-1!=(read=fileInputStream.read(b))){
                //System.out.println((char) read);
                System.out.println(new String(b,0,read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(fileInputStream!=null){
                try{
                    fileInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static String output="";
    @Test
    public void test23(){
        foo(0);
        foo(1);
        System.out.println(output);
    }

    public static void foo(int i){
        try{
            if(i==1){
                throw new Exception();
            }
        }catch (Exception e){
            output+="2";
            return;
        }finally {
            output+="3";
        }
        output+="4";
    }

    @Test
    public void test24(){
        //String Regex="^[0-9]+[.][0-9]{2}$";
        //String Regex="^100$|^([0-9]|[1-9][0-9])([.][0-9])*$";
        String Regex="^100$|^([0-9]|[1-9][0-9])([.][0-9])$|^([0-9]|[1-9][0-9])([.][0-9]{2})*$";
        System.out.println("0.00".matches(Regex));
    }

    @Test
    public void test25(){
        //String Regex="^100$|^([0-9]|[1-9][0-9])([.][0-9]+)*$";
        String Regex="^[0-9]+$|^([0-9]+)([.][0-9])$|^([0-9]+)([.][0-9]{2})$";
        System.out.println("6516515.2".matches(Regex));
    }

    @Test
    public void test26(){
        String Regex="^[1][0]$|^[0-9]$|^([0-9])([.][0-9])$";
        System.out.println("10".matches(Regex));
    }

    @Test
    public void test27(){
        //String Regex="^[1-9][0-9]{3}$";
        String Regex="^[0-9]$|^[1-9][0-9]+$";
        System.out.println("1".matches(Regex));
    }

    @Test
    public void test28(){
        String Regex="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(Regex);
        Matcher mat = pat.matcher("2018/1/32");
        boolean dateType = mat.matches();
        System.out.println(dateType);
    }

    @Test
    public void test29(){
        String Regex="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\s]?((((0?[13578])|(1[02]))[\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\s]?((((0?[13578])|(1[02]))[\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$";
        System.out.println("2018-01-01".matches(Regex));
    }

    @Test
    public void test30(){
        String Regex="^([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01])$";
        System.out.println("2018-02-31".matches(Regex));
    }

    @Test
    public void test31(){
        String Regex="^(([1][9]|[2][0])([789][0-9]|[0-9]{2})[-]([0][1-9]|[1][012])[-]([0][1-9]|[1][0-9]|[2][0-9]|[3][01]))[ ]((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        System.out.println("2018-01-31 05:0:00".matches(Regex));
    }
}
