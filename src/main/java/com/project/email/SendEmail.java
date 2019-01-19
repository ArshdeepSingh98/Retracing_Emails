package com.project.email;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmail {

	public static String getCID() {
		Random random = new Random();
		return new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date()) + random.nextInt(50);
	}

	public static void main(String[] args) {
		
		String to = "arshdeep.mailme@gmail.com";
		String from = "arshdeep.mailme@gmail.com";
		
		final String username = "email";
		final String password = "pass";
	    
		String host = "smtp.gmail.com";
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "25");
	    
	    Session session = Session.getInstance(props,
	    		new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(username, password);	
					}	
				});
	    try {
	    	Message message = new MimeMessage(session);
	    	message.setFrom(new InternetAddress(from));
	    	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    	message.setSubject("Testing subject");
	    	Multipart multipart = new MimeMultipart();
	    	
	    	String embeddedCid = getCID();
	    	
	    	MimeBodyPart textPart = new MimeBodyPart();
//	    	textPart.setText("<html><head>" + "<title></title>" + "</head>\n" + "<body><div><strong>Hi there!</strong></div>" + "<div>Sending HTML in email is so <em>cool!</em> </div>" + "<div>And here's an image: <img src=cid:" + embeddedCid + "> </div>" + "<div>I hope you like it!</div></body></html>", "US-ASCII","html");
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
	    	Date date = new Date();
	    	String dateString = formatter.format(date);
	    	String timeString = formatter2.format(date);
	    	textPart.setText("<html><head>" + "<title></title>" + "</head>\n" + "<body><div><strong>Hi there!</strong></div>" + "<div>Sending HTML in email is so <em>cool!</em> </div>" + "<div>And here's an image: <img src="+ "\"file://"+ "127.0.0.1" + "/Users/oyo/Desktop/tracker/senddata.html" + "?" + "email="+ to +"&cid="+ embeddedCid +"&time="+ timeString +"&date=" + dateString +"\"" + "> </div>" + "<div>If image doesn't load click<a href=" + "\"file://"+ "127.0.0.1" + "/Users/oyo/Desktop/tracker/senddata.html" + "?" + "email="+ to +"&cid="+ embeddedCid +"&time="+ timeString +"&date=" + dateString +"\""+">this</a></div>"+"<div>I hope you like it!</div></body></html>", "US-ASCII","html");
	    	
	    	multipart.addBodyPart(textPart);
	    	
//	    	MimeBodyPart imagePart = new MimeBodyPart();
//	    	String fn = "/Users/oyo/Desktop/tracker/senddata.html?email="+ to +"&cid="+ embeddedCid +"&time="+ timeString +"&date=" + dateString;
//	    	DataSource source = new FileDataSource(fn);
//	        imagePart.setDataHandler(new DataHandler(source));
//	        imagePart.setFileName("Image File 1");
//	        imagePart.setHeader("Content-ID", "<" + embeddedCid + ">");
//	        imagePart.setDisposition(MimeBodyPart.INLINE);
//	        multipart.addBodyPart(imagePart);
	    	
	        message.setContent(multipart);
	       
	        Transport.send(message);
	        System.out.println("Sent message successfully....");
	    }catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
}
