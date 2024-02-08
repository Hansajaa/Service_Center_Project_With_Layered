package Bo.Custom.Impl;

import Bo.BoFactory;
import Bo.Custom.ChangeStatusAndZoneBo;
import Bo.Custom.OrderBo;
import Dao.Custom.ChangeStatusAndZoneDao;
import Dao.DaoFactory;
import Dao.util.BoType;
import Dao.util.DaoType;
import Dto.ChangeStatusAndZoneDto;
import Dto.OrderDto;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChangeStatusAndZoneBoImpl implements ChangeStatusAndZoneBo {

    ChangeStatusAndZoneDao changeStatusAndZoneDao= DaoFactory.getInstance().daoType(DaoType.CHANGE_STATUS_ZONE);

    OrderBo orderBo= BoFactory.getInstance().boType(BoType.ORDER);

    @Override
    public List<String> getStatuses() {
        List<String> statuses=new ArrayList<>();

        statuses.add("Pending");
        statuses.add("Processing");
        statuses.add("Completed");
        statuses.add("Closed");

        return statuses;
    }

    @Override
    public List<String> getZones() {
        List<String> zones=new ArrayList<>();

        zones.add("None");
        zones.add("Orange Zone");
        zones.add("Red Zone");
        zones.add("Yellow Zone");
        zones.add("Green Zone");

        return zones;
    }

    @Override
    public boolean update(ChangeStatusAndZoneDto dto) {
        boolean isUpdated = changeStatusAndZoneDao.updateStatusAndZone(dto);

        return isUpdated;
    }

    @Override
    public boolean sendAlert(String orderId) {
        OrderDto order = orderBo.getOrder(orderId);

        String to = order.getCustomerEmail();
        String subject = "Your Order Alert";

        String message = "Order Id :"+order.getOrderId()+"<br>"+"Your order is "+order.getStatus();

        sendMail(to,subject,message);

        return true;
    }

    @Override
    public boolean sendBill(String orderId) {
        OrderDto order = orderBo.getOrder(orderId);

        String to = order.getCustomerEmail();
        String subject = "Your Order Bill";

        String id = "Order Id : "+order.getOrderId();
        String item = "Item : "+order.getMainItem();
        String category = "Category : "+order.getCategory();
        String desc = "Description : "+order.getDescription();
        String date = "Date : "+order.getDate();
        String tot = "Total : "+order.getTotal();
        String status = "Status : "+order.getStatus();
        String serviceCharge = "Service Charge : "+order.getServiceCharge();
        String message = id+"<br>"+item+"<br>"+category+"<br>"+desc+"<br>"+date+"<br>"+tot+"<br>"+status+"<br>"+serviceCharge;

        sendMail(to,subject,message);

        return true;
    }

    @Override
    public void sendMail(String to, String subject, String message) {
        // Replace these values with your email configuration
        final String from = "hansajanilana@gmail.com";
        final String password = "efjm axwy atmg ubsj";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(to));
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
