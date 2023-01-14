package com.doniso.Doniso.Email;

import com.doniso.Doniso.Models.DemandAudit;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class EmailConstructor {

    @Autowired
    private Environment env;

    @Autowired
    private TemplateEngine templateEngine;

    // Permet de Communiquer avec nos différent Service controller
    public MimeMessagePreparator constructNewUserEmail(Utilisateurs user) {
        Context context = new Context();
        context.setVariable("user", user);
        String text = templateEngine.process("newUserEmailTemplate", context); // Nom fichier html
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("Bienvenue sur Doniso");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    // Template Acceptation demande formation :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public MimeMessagePreparator constructDemandAuditEmail(DemandAudit demandAudit) {
        Context context = new Context();
        context.setVariable("audit", demandAudit);
        String text = templateEngine.process("AccepterAuditEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(demandAudit.getEmail());
                email.setSubject("Bienvenue dans Doniso");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    // Template Refus demande formation :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public MimeMessagePreparator constructRefusAuditEmail(DemandAudit demandAudit) {
        Context context = new Context();
        context.setVariable("audit", demandAudit);
        String text = templateEngine.process("RefuserAuditEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(demandAudit.getEmail());
                email.setSubject("Bienvenue dans Doniso");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructResetPasswordEmail(Utilisateurs user, String password) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        String text = templateEngine.process("resetPasswordEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("New Password - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructUpdateUserProfileEmail(Utilisateurs user) {
        Context context = new Context();
        context.setVariable("user", user);
        String text = templateEngine.process("updateUserProfileEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("Profile Update - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }
}