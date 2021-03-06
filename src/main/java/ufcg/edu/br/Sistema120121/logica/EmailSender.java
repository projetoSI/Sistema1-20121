package ufcg.edu.br.Sistema120121.logica;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

	// Método que envia o email
	public boolean enviaEmail(String user, String destinatario, String mensagem) {

		Session session = Session.getDefaultInstance(getPropriedades(), getAuthenticator());

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("notification@sistema120121.com")); // Seta o remetente
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); // Define o destinatário
			message.setSubject("Notification"); // Define o assunto
			message.setText(user + "\n\n" + mensagem); // Mensagem do email

//			POR ENQUANTO, NAO ENVIA NENHUM EMAIL POIS O SERVIDOR PRORPIO NAO ESTA PRONTO
//			Transport.send(message); // Envia o email

			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

	// Método que retorna a autenticação de sua conta de email
	private Authenticator getAuthenticator() {

		Authenticator autenticacao = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {

				// Preencher com seu email e com sua senha
				return new PasswordAuthentication("teste@gmail.com", "senha");
			}
		};

		return autenticacao;
	}

	// Método que retorna as propriedades de configuração do servidor de email
	private Properties getPropriedades() {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP do seu servidor de email
		props.put("mail.smtp.socketFactory.port", "465"); // Porta do servidor smtp
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Define a conexão do tipo SSL
		props.put("mail.smtp.auth", "true"); // Define que é necessário autenticação
		props.put("mail.smtp.port", "465"); // Porta do seu servidor smtp

		return props;
	}
}