package sistema;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import users.Utilizador;

public class EmailHelper {
	Session newSession;
	public EmailHelper() {
		Properties props = System.getProperties();
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		//props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.enable", "true");
		
		newSession = Session.getDefaultInstance(props,null);
	}
	
	public void SendMailConfirmed(Utilizador u) {
		
		
		String[] rec = {u.getEmail()};
		String emailSub = "Conta Aceite!";
		String emailBod = "Olá, \n A sua conta foi registada e confirmada com sucesso!\n Os detalhes da sua conta: \n UserName:" + u.getLogin() + "\n Password:"+u.getPassword();
		
		MimeMessage mes = new MimeMessage(newSession);
		
		try {
			mes.addRecipient(Message.RecipientType.TO, new InternetAddress(rec[0]));
			MimeBodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(emailBod,"text/html; charset=utf-8");
			//bodypart.setHeader(emailSub, emailBod);
			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(bodypart);
			
			
			
			mes.setContent(multiPart);
			
			mes.saveChanges();
			
			Transport transport = newSession.getTransport("smtp");
			transport.connect("smtp.gmail.com","andre123convidado4@gmail.com","elddydtetrcxpsci");
			transport.sendMessage(mes, mes.getAllRecipients());
			transport.close();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		
		
	}
	
	
}
