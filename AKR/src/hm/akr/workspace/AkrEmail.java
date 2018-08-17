package hm.akr.workspace;

import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;



/**
 * Class to send mail
 * 
 * 
 */
public class AkrEmail {
	public static final String MAIL_HOST = "smtp.gmail.com";
	public static final String SENDER = "akrbpa@gmail.com";

	Date date = new Date();
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String date1 = dateFormat.format(date);

	public static String SUBJECT = "";

	public static String BODY = "DailyStatus";

	/**
	 * Method to send email
	 * 
	 * @param subject
	 * @param body
	 * @param sender
	 * @param recipients
	 * @param filename
	 * @param filePath
	 * @throws Exception
	 */
	public synchronized boolean sendMail(String recipients, String filename,
			String filePath)

	{
		boolean sent = false;

		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", MAIL_HOST);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.quitwait", "false");

			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"akrbpa@gmail.com", "aalavanthaan");
						}
					});

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(SENDER));

			// message.setContent(body, "text/plain");
			if (recipients.indexOf(',') > 0)
				message.setRecipients(Message.RecipientType.TO, InternetAddress
						.parse(recipients));
			else {
				message.setRecipient(Message.RecipientType.TO,
						new InternetAddress(recipients));
			}

			if (SUBJECT.equals("")) {
				message.setSubject(date1);
			} else {
				message.setSubject(SUBJECT);
			}

			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(BODY);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);

			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// Send the message
			Transport.send(message);
			sent = true;
			System.out.println("Mail Sent to :" + recipients);
		} catch (Exception exception) {
			sent = false;
			System.out.println("Mail not sent to:" + recipients);
		}

		return sent;
	}

}