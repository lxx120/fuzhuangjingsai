package com.match.common.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailmethod {
	public static boolean mailsend(String dest,String title,String content)
	{
		if(dest==null)
			return false;
		Properties ps=new Properties();
		ps.setProperty("mail.transport.protocol", "smtp");
		ps.setProperty("mail.smtp.auth", "true");
		ps.setProperty("mail.smtp.host", "smtp.126.com");
		Session s=Session.getInstance(ps,new Ma());
		s.setDebug(false);
		Message m=new MimeMessage(s);
		//如果复杂内哟，要用Mimebodypart
		/*MimeBodyPart bodyPartAttch = createAttachMent("C:\\Users\\Administrator\\Desktop\\mail.jar");//附件
MimeBodyPart bodyPartContentAndPic = createContentAndPic("I just want to Fuck","C:\\Users\\Administrator\\Desktop\\0.jpg");//文本内容
MimeMultipart mimeMuti = new MimeMultipart("mixed");
mimeMuti.addBodyPart(bodyPartAttch);
mimeMuti.addBodyPart(bodyPartContentAndPic);
message.setContent(mimeMuti);
message.saveChanges();
		 * */
		try {
			m.setFrom(new InternetAddress(Ma.userName));
			m.setSubject(title);
			String[] dests=dest.split(";");
			for (String string : dests) {
				m.setRecipients(RecipientType.TO, InternetAddress.parse(string));
			}
			m.setText(content);
			Transport.send(m);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	 
}
