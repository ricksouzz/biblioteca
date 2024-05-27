package br.com.techlead.biblioteca.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
	private final TemplateEngine templateEngine;

    public EmailService(final JavaMailSender javaMailSender, final  TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Transactional
    public String enviar(String destinatario, String assunto, String corpo) throws Exception {
        MimeMessage mensagem = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);

            helper.setFrom(username);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpo, true);

            javaMailSender.send(mensagem);
            log.info("email enviado com sucesso!");

            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao enviar e-mail: " + e.getMessage());
        }
        return destinatario;
    }
}