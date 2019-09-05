package hwkj.hwkj;

import org.junit.Test;

import java.util.*;

public class Algorithm {

    @Test
    public void Test1(){
        System.out.println(recursion(10));
        System.out.println(recursion1(10));
    }

    /**
     * 编写一个程序，输入n,求n！（用递归的方式实现）。
     * @param n
     * @return
     */
    public static int recursion(int n){
        if(n<=0){
            return 0;
        }
        else if(n==1){
            return 1;
        }
        else {
            return n*recursion(n-1);
        }
    }

    /**
     * 编写一个程序，输入n,求n！
     * @param n
     * @return
     */
    public static int recursion1(int n){
        if(n<=0){
            return 0;
        }
        else if(n==1){
            return 1;
        }
        else {
            int sum=1;
            for(int i=n;i>0;i--){
                sum=i*sum;
            }
            return sum;
        }
    }

    /**
     * 编写一个程序，有1，2,3,4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     */
    @Test
    public void Test2(){
        int a[]={5,6,7,8};
        int m=0;
        int length=a.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                for(int k=0;k<length;k++){
                    if(a[i]!=a[j] && a[i]!=a[k] && a[j]!=a[k]){
                        System.out.println(""+a[i]+a[j]+a[k]);
                        m++;
                    }
                }
            }
        }
        System.out.println("能组成："+m+"个");
    }

    /**
     * 639172每个位数上的数字都是不同的，且平方后所得数字的所有位数都不会出现组成它自身的数字。
     *（639172*639172=408540845584），类似于639172这样的6位数还有几个？分别是什么？
     */
    @Test
    public void Test3(){
        for(int i=100000;i<1000000;i++){
            char num[]=String.valueOf(i).toCharArray();
            if(num[0]!=num[1] && num[0]!=num[2] && num[0]!=num[3] && num[0]!=num[4] && num[0]!=num[5] &&
            num[1]!=num[2] && num[1]!=num[3] && num[1]!=num[4] && num[1]!=num[5] &&
            num[2]!=num[3] && num[2]!=num[4] && num[2]!=num[5] &&
            num[3]!=num[4] && num[3]!=num[5] &&
            num[4]!=num[5]){
                long PingFang=(long)i*i;
                String string=String.valueOf(PingFang);
                if((!string.contains(String.valueOf(i).substring(0,1))) &&
                        (!string.contains(String.valueOf(i).substring(1,2))) &&
                        (!string.contains(String.valueOf(i).substring(2,3))) &&
                        (!string.contains(String.valueOf(i).substring(3,4))) &&
                        (!string.contains(String.valueOf(i).substring(4,5))) &&
                        (!string.contains(String.valueOf(i).substring(5,6)))){
                    System.out.println("初始值为:"+i);
                    System.out.println("平方值为:"+PingFang);
                }
            }
        }
    }

    /**
     * 比如,968548+968545=321732732它的答案里没有前面两个数里的数字，有多少这样的6位数
     */
    @Test
    public void Test4(){
        long num=(long) 968548*968545;
        System.out.println(num);
    }

    /**
     * 给定String,求此字符串的单词数量。字符串不包括标点，大写字母
     * 例如 String str="hello world hello hi";单词数量为3，分别是：hello world hi
     */
    @Test
    public void Test5(){
        int count=0;
        String newStr="";
        Map<String,String> map=new HashMap<>();
        String str="hello world hello hi";
        String array[]=str.split(" ");
        for(int i=0,length=array.length;i<length;i++){
            if(!map.containsKey(array[i])){
                map.put(array[i],"1");
                count++;
                newStr=newStr+" "+array[i];
            }
        }
        System.out.println("这段短文单词的个数是:"+count+","+"分别是:"+newStr);
    }

    @Test
    public void Test6(){
        compress("aaabbcaa");
        compress("a");
        compress("abcabc");
        compress("ppppp");
        compress("");
        compress(null);
    }

    /**
     * 字符串压缩:
     * 写一个函数,把连续的字符串进行压缩,无需要考虐编码,40分钟以内完成
     * 例如:输入"aaabbcaa","a",""  输出"a3b2ca2","a",""
     */
    public static void compress(String string){
        int count=0,index=0;
        String newStr="";
        if(string==null || "".equals(string)){
            System.out.println("");
            return;
        }
        char array[]=string.toCharArray();
        for(int i=0,length=array.length;i<length;i++){
            if(i+1>=length){
                break;
            }
            count++;
            while(array[i]!=array[i+1]){
                if(1==count){
                    newStr=newStr+String.valueOf(array[i])+"";
                }else {
                    newStr=newStr+String.valueOf(array[i])+count;
                }
                count=0;
                index=i+1;
                break;
            }
        }
        System.out.println(newStr+array[index]+(array.length-index==1?"":array.length-index));
    }

    @Test
    public void Test7(){
        encode("aaabbcaa");
        encode("a");
        encode("abcabc");
        encode("ppppp");
        encode("");
        encode(null);
    }

    /**
     * 字符串压缩:
     * 写一个函数,把连续的字符串进行压缩,无需要考虐编码,40分钟以内完成
     * 例如:输入"aaabbcaa","a",""  输出"a3b2ca2","a",""
     */
    public static void encode(String str) {
        if (str == null || str.equals("")) {
            System.out.println("");
            return;
        }
        StringBuffer sb = new StringBuffer();
        int count = 1;
        char element = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == element) {
                count++;
            } else {
                sb.append(element);
                sb.append(count == 1 ? "" : count);
                element = str.charAt(i);
                count = 1;
            }
        }
        sb.append(element);
        sb.append(count == 1 ? "" : count);
        System.out.println(sb.toString());
    }

    /**
     * 输入一个矩阵,按照从外向里以顺时针的顺序依次打印出每一个数字
     * 例如:
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     * 则依次打印数字为:1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
     */
    @Test
    public void Test8(){
        int[][] num = new int[100][100];
        int row = 4;
        int cell =4;
        int count =0;
        for(int i=0;i<row;i++){
            for(int j =0;j<cell;j++){
                count++;
                num[i][j]=count;
            }
        }
        output(num,0,row,cell);
    }

    public static void output(int[][] num,int start,int row,int cell){
        if(start>row || cell<=0){
            return;
        }
        for(int i=start;i<cell;i++){
            System.out.print(num[start][i]+",");
        }
        for(int i=start+1;i<row;i++){
            System.out.print(num[i][cell-1]+",");
        }
        for(int i=cell-1-1;i>=start;i--){
            System.out.print(num[row-1][i]+",");
        }
        for(int i=row-1-1;i>start;i--){
            System.out.print(num[i][start]+",");
        }
        output(num,start+1,row-1,cell-1);
    }

    /**
     * 题目:输入一个字符串,打印出该字符串中字符的所有排列
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串
     * abc,acb
     * bac,bca
     * cab,cba
     */
    @Test
    public void Test9(){
        combination("abc");
    }

    public static void combination(String string){
        StringBuffer sb=null;
        char array[]=string.toCharArray();
        for(int i=0,length=array.length;i<length;i++){
            sb=new StringBuffer();
            boolean flag=true;
            for(int k=0;k<array.length-1;k++){

            }
            for(int j=0;j<array.length;j++){
                if(array[i]!=array[j]){
                    if(flag){
                        sb=sb.append(array[i]);
                        flag=false;
                    }
                    sb=sb.append(array[j]);
                }
            }
            System.out.println(sb.toString());
        }
    }

    @Test
    public void Test10(){
        String string="abc";
        char array1[]=string.toCharArray();
        char array2[]=Arrays.copyOf(array1,array1.length);
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array2.length;j++){
                System.out.println(array1[i]);
            }
        }
    }

    @Test
    public void Test11(){
        char[] cs = {'a','b','c','d','e'};
        //char[] cs = {'a','b','c'};
        int length = cs.length;
        recursionSwap(cs,0,length);
    }

    public void swap(char[] cs,int index1,int index2){
        char temp = cs[index1];
        cs[index1]=cs[index2];
        cs[index2]=temp;
    }

    public void recursionSwap(char[] cs,int start,int length){
        if(start>=length-1){
            print(cs);
            return;
        }
        for(int i=start;i<length;i++){
            swap(cs,start,i);
            recursionSwap(cs,start+1,length);
            swap(cs,start,i);
        }
    }

    public void print(char[] cs){
        for(int i=0;i<cs.length;i++){
            System.out.print(cs[i]);
        }
        System.out.println();
    }

    @Test
    public void test(){
        int[] num = {1,2,2,3,4,5,6,7,8,9};
        int sum = 7;
        findSum(num,sum);
    }

    public void findSum(int[] num,int sum){
        int left=0;
        int right=0;
        for(int i=0;i<num.length;i++){
            int curSum = 0;
            left = i;
            right = i;
            while(curSum<sum){
                curSum += num[right++];
            }
            if(curSum==sum){
                for(int j=left;j<right;j++){
                    System.out.print(num[j]+" ");
                }
                System.out.println();
            }
        }
    }

    /**
     * 不用正则表达式校验邮箱
     */
    @Test
    public void Test12(){
        System.out.println(validateEmail("9314中66973@qq.com"));
    }

    public static boolean validateEmail(String email){
        int pos=email.indexOf("@");
        if(pos==-1 || pos==0 || email.length()==1){
            return false;
        }
        String string[]=email.split("@");
        if(string.length!=2){
            return false;
        }
        CharSequence charSequence=string[0];
        for(int i=0;i<charSequence.length();i++){
            char c=charSequence.charAt(i);
            if(!Character.isLetter(c) && !Character.isDigit(c)){
                return false;
            }
        }
        pos = string[1].indexOf(".");// 如果@后面没有.，则是错误的邮箱
        if(pos==-1 || pos==0 || string[1].length()==1){
            return false;
        }
        string=string[1].split(".");
        for(int i=0;i<string.length;i++){
            charSequence=string[i];
            if(charSequence.length()==0){
                return false;
            }
            for(int j=0;j<charSequence.length();j++){
                char c=charSequence.charAt(j);
                if(!Character.isLetter(c) && !Character.isDigit(c)){
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void Test13(){
        Random r1=new Random();
        Random r2=new Random();
        System.out.println(r1.nextFloat()+";"+r2.nextFloat());
        System.out.println(r1.nextDouble()+100+";"+r2.nextDouble());
        System.out.println(r1.nextInt(100)-50+";"+r2.nextInt(100));
        System.out.println(r1.nextLong()+";"+r2.nextLong());
        System.out.println(r1.nextBoolean()+";"+r2.nextBoolean());
    }

    /**
     * 水仙花数
     * 例如153=1^3+5^3+3^3;
     */
    @Test
    public void Test14(){
        int b,s,g;
        for(int i=100;i<1000;i++){
            b=i/100;
            s=(i-b*100)/10;
            g=(i-b*100-s*10);
            if(i==b*b*b+s*s*s+g*g*g){
                System.out.println(i);
            }
        }
    }

    /**
     * 	输入一整数 A，判断它是否质数
     *  质数:质数又称素数。指在一个大于1的自然数中，除了1和此整数自身外，没法被其他自然数整除
     *  整除:整除是指整数a除以自然数b除得的商正好是整数而余数是零
     *  思路:循环除比自己小的数字如果取余不等于0则为质数
     *  例子: 5 % 2 = 1 ,  6 % 2 = 0 ,  7 % 2 = 1 , 8 % 2 = 0
     *  规律:质数取余!=0
     *
     */
    @Test
    public void Test15(){
        PrimeNumber(-1);
        PrimeNumber(1);
        PrimeNumber(15);
        PrimeNumber(23);
    }

    public static void PrimeNumber(int number){
        boolean flag =true;
        if(number<=0){
            flag=false;
            System.out.println("请输入大于0的正整数");
        }
        if(1==number){
            flag=false;
            System.out.println(number + "是质数");
        }
        for(int i=2;i<number;i++){
            if(number%i==0){
                System.out.println(number + "不是质数");
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println(number + "是质数");
        }
    }

    /**
     * 求100-10000之间的质数,并输出每一个质数及质数总和
     */
    @Test
    public void Test16(){
        int sum=0;
        boolean flag;
        for(int i=100;i<=10000;i++){
            flag=true;
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag){
                sum=sum+i;
                System.out.println(i);
            }
        }
        System.out.println(sum);
    }

    /**
     * 以1为首项，公比为2，求前64位数的总和
     */
    @Test
    public void Test17(){
        double sum=0;
        for(int i=0;i<=63;i++){
            double pow=Math.pow(2,i);
            sum=sum+pow;
        }
        System.out.println(sum);
    }

    /**
     *将数组中的n个整数按相反的顺序输出
     */
    @Test
    public void Test18(){
        array(new int[]{5,6,9,2,8,56,10});
    }

    public static void array(int array[]){
        int temp;
        for(int i=array.length-1,j=0;i>=array.length/2;i--,j++){
            temp=array[i];
            array[i]=array[j];
            array[j]=temp;
        }
        for(int i=0,length=array.length;i<length;i++){
            System.out.println(array[i]);
        }
    }

    /**
     * 输入一个字符串,将小写字母转成大写字母,大写字母转成小写字母
     */
    @Test
    public void Test19(){
        change("SS@n@js@SDD");
        change("s1b2h@DS#D#F14@15!");
    }

    public static void change(String string){
        char array[]=string.toCharArray();
        for(int i=0,length=array.length;i<length;i++){
            if(array[i]>=65 && array[i]<=90){
                array[i]=(char)((int)array[i]+32);
            }
            else if(array[i]>=97 && array[i]<=122){
                array[i]=(char)((int)array[i]-32);
            }
        }
        string=String.valueOf(array);
        System.out.println(string);
    }

    /**
     * 求2个数的最大公约数
     */
    @Test
    public void Test20(){
        GreatestCommonDivisor(15,55);
        GreatestCommonDivisor(-1,55);
        GreatestCommonDivisor(15,2);
        GreatestCommonDivisor(15,1);
        GreatestCommonDivisor(15,15);
    }

    public static void GreatestCommonDivisor(int num1,int num2){
        int value=0,greatestCommonDivisor=0;
        if(num1<=1 || num2<=1){
            System.out.println("请输入大于1的2个整数");
            return;
        }
        else if(num1==num2){
            System.out.println(num1+"和"+num2+"的最大公约数为:("+num1+","+num2+")="+num1);
            return;
        }
        else if(num1<num2){
            value=num1;
        }
        else {
            value=num2;
        }
        for(int i=2;i<=value;i++){
            if(num1%i==0 && num2%i==0){
                greatestCommonDivisor=i;
            }
        }
        if(greatestCommonDivisor==0){
            System.out.println(num1+"和"+num2+"没有最大公约数");
            return;
        }
        System.out.println(num1+"和"+num2+"的最大公约数为:("+num1+","+num2+")="+greatestCommonDivisor);
    }

    /**
     * 求2个数的最小公倍数
     */
    @Test
    public void Test21(){
        MinimumCommonMultiple(15,12);
        MinimumCommonMultiple(-1,12);
        MinimumCommonMultiple(15,19);
        MinimumCommonMultiple(15,15);
        MinimumCommonMultiple(15,0);
    }

    public static void MinimumCommonMultiple(int num1,int num2){
        int value=0,greatestCommonDivisor=0,minimumCommonMultiple=0;
        if(num1<=1 || num2<=1){
            System.out.println("请输入大于1的2个整数");
            return;
        }
        else if(num1==num2){
            System.out.println(num1+"和"+num2+"的最小公倍数为:("+num1+","+num2+")="+num1);
            return;
        }
        else if(num1<num2){
            value=num1;
        }
        else {
            value=num2;
        }
        for(int i=2;i<=value;i++){
            if(num1%i==0 && num2%i==0){
                greatestCommonDivisor=i;
            }
        }
        if(greatestCommonDivisor==0){
            System.out.println(num1+"和"+num2+"的最小公倍数为:("+num1+","+num2+")="+num1*num2);
            return;
        }
        minimumCommonMultiple=(num1/greatestCommonDivisor)*(num2/greatestCommonDivisor)*greatestCommonDivisor;
        System.out.println(num1+"和"+num2+"的最小公倍数为:("+num1+","+num2+")="+minimumCommonMultiple);
    }
}

