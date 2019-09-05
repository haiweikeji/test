package hwkj.hwkj;

import hwkj.hwkj.cache.RedisCache;
import hwkj.hwkj.utils.getIp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HwkjApplicationTests {

    private final Logger logger= LoggerFactory.getLogger(HwkjApplicationTests.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisCache redisCache;

    @Value("${spring.mail.username}")
    private String username;

    @Test
    public void sendHtmlMail(){
        String localhostIp= getIp.getLocalHostAddress();
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(username);
            mimeMessageHelper.setTo(new String[]{"shenquan@h-wei.com"});//,"sunjing@h-wei.com"
            mimeMessageHelper.setSubject("海潍系统通知");
            String content="<html>\n" +
                    "<body>\n" +
                    "<h3>您有一笔数据"+"<a href='"+localhostIp+":8080/hwkj/login.do'>待签核</a></h3>"+
                    "</body>\n" +
                    "</html>";
            mimeMessageHelper.setText(content,true);
            javaMailSender.send(mimeMessage);
            logger.info("");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("",e);
        }
    }

    @Test
    public void contextLoads() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=simpleDateFormat.format(new Date());
        StringBuffer stringBuffer=new StringBuffer("当前时间为:");
        stringBuffer=stringBuffer.append(time);
        time=stringBuffer.toString();
        System.out.println(time);
    }

    @Test
    public void getLocalHostAddress(){
        //InetAddress jdkSuppliedAddress = null;
        String  jdkSuppliedAddress = null;
        try {
            jdkSuppliedAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(jdkSuppliedAddress);
    }

    /**
     * 获取机器所有网卡的IP（ipv4）
     * @return
     */
    @Test
    public void getLocalIP() {
        List<String> ipList = new ArrayList<>();
        InetAddress ip = null;
        try {
            Enumeration<NetworkInterface> netInterfaces =NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni =netInterfaces.nextElement();
                // 遍历所有ip
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (null == ip || "".equals(ip)) {
                        continue;
                    }
                    String sIP = ip.getHostAddress();
                    if(sIP == null || sIP.indexOf(":") > -1) {
                        continue;
                    }
                    if(!"127.0.0.1".equals(sIP)){
                        ipList.add(sIP);
                        System.out.println(sIP);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void set(){
        redisCache.set("name","ShenQuan");
    }

    @Test
    public void get(){
        redisCache.get("name");
    }
}
