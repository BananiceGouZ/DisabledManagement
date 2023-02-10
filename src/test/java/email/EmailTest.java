package email;


import cn.bananice.PetHomeApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = PetHomeApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void  send(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置发送人
        mailMessage.setFrom("1640986892@qq.com");
        //邮件主题
        mailMessage.setSubject("新型冠状病毒防护指南");
        //邮件内容
        mailMessage.setText("好好在家待着.....");
        //收件人
        mailMessage.setTo("741901200@qq.com");

        javaMailSender.send(mailMessage);
    }

}
