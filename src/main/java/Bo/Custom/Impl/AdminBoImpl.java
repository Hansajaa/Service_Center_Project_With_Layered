package Bo.Custom.Impl;

import Bo.Custom.AdminBo;
import Dao.Custom.AdminDao;
import Dao.DaoFactory;
import Dao.util.DaoType;
import Dto.AdminDto;
import Entity.AdminEntity;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import javafx.scene.control.Alert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class AdminBoImpl implements AdminBo {

    AdminDao dao = DaoFactory.getInstance().daoType(DaoType.ADMIN);
    @Override
    public boolean changePassword(AdminDto dto) {
        return dao.update(
                new AdminEntity(
                        dto.getEmail(),
                        encryptPassword(dto.getPassword())
                )
        );
    }

    @Override
    public boolean authenticate(AdminDto dto) {
        AdminEntity admin = dao.authenticate(dto.getEmail());

        if (admin.getPassword().equals(encryptPassword(dto.getPassword()))){
            return true;
        }
        return false;
    }

    @Override
    public String encryptPassword(String password) {
        /* Plain-text password initialization. */
        String encryptedpassword = null;
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encryptedpassword;
    }

    @Override
    public String sendOtp(String mail) {
        AdminEntity admin = dao.authenticate(mail);

        if (admin!=null){
            String otp = generateOtp();
            mailSend(mail,"OTP Code",otp);
            return otp;
        }else {
            return null;
        }
    }

    @Override
    public String generateOtp() {
        String numbers = "0123456789";
        Random r = new Random();
        char[] otp = new char[6];
        for (int i = 0; i < 6; i++) {
            otp[i] = numbers.charAt(r.nextInt(numbers.length()));
        }
        return new String(otp);
    }

    @Override
    public void mailSend(String mail, String subject, String message){
        // Replace these values with your email configuration
        final String from = "hansajanilana@gmail.com";
        final String password = "efjm axwy atmg ubsj";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        jakarta.mail.Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            jakarta.mail.Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(mail));
            msg.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(message, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            msg.setContent(multipart);

            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
