package eng.tz.la.core;

import eng.tz.la.model.Userlog;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

import eng.tz.la.model.AuditSerial;
import eng.tz.la.model.LogSettyng;

/**
 *
 * @author s.mariniello
 */
public class SerializeData implements Serializable {
    
    private LogSettyng settyng= new LogSettyng();
    private AuditSerial audit = new AuditSerial();
    private Userlog lAlog = null;
    private final String folderDay = new SimpleDateFormat("ddMMyyyy").format(new Date(System.currentTimeMillis()));
    public final String path_home = System.getProperty("user.dir") + File.separator+"audit-log"+ File.separator;
    private final String path_file = System.getProperty("user.dir") + File.separator+"audit-log"+ File.separator;
    public final String path_log =  folderDay + File.separator;
    private final String logAllNameFile = "laAuditAll.file";
    private final String logAllNameLog = "allUserLogs.txt";
    private final String logExtension=".txt";
    private final String logFileExtension=".file";


    public SerializeData(AuditSerial audit) {
        this.audit = audit;
    }

    public SerializeData() {

    }

    protected void salvaHome(String userName, Userlog lAlog) {
        ObjectOutputStream out = null;
        try {
            String pathComplete=getPath_File() + checkLogAllName(settyng.getLogName());
            out = new ObjectOutputStream(new FileOutputStream(pathComplete));
            audit.put(userName, lAlog);
            out.writeObject(audit);

        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {

            }
        }

    }

    protected void salva(AuditSerial lAudit) {
        ObjectOutputStream out = null;
        try {
            String pathComplete=getPath_File() + checkLogAllName(settyng.getLogName());
            out = new ObjectOutputStream(new FileOutputStream(pathComplete));
            out.writeObject(lAudit);
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {

            }
        }

    }

    protected void salva(Userlog alog, String userName) {
    	 
    	ObjectOutputStream out = null;
        try {
            String pathComplete=getPath_File_User() + userName + checkFileExtension();
            out = new ObjectOutputStream(new FileOutputStream(pathComplete));
            out.writeObject(alog);
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {

            }
        }

    }

    protected AuditSerial getLAudit() {
        try {
            ceckDir();
            String pathComplete=getPath_File() + checkLogAllName(settyng.getLogName());
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathComplete));
            audit = (AuditSerial) in.readObject();

            in.close();

            return audit;

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(SerializeData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializeData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    protected Userlog getUserlogFile(String userName) {
        try {

            ceckDir(userName);
            String pathComplete=getPath_File_User() + userName +checkFileExtension();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathComplete));
            lAlog = (Userlog) in.readObject();

            in.close();

            return lAlog;

        } catch (FileNotFoundException e) {

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(SerializeData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializeData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private boolean ceckDir() {

        File f = new File(getPath_File() + settyng.getLogName());
        if (!f.exists()) {
            File ffd = new File(getPath_File());
            if (!ffd.exists()) {
                boolean bol = ffd.mkdirs();
               salva(new AuditSerial());
                return bol;
            } 
        }
        return true;
    }

    private boolean ceckDir(String userName) {
    	 
        File f = new File(getPath_File_User() + userName + checkFileExtension());
        if (!f.exists()) {
            File ffd = new File(getPath_File_User());
            if (!ffd.exists()) {
                boolean bol = ffd.mkdirs();
                salva(new Userlog(userName), userName);
                return bol;
            } 
        }
        return true;
    }

    protected boolean checkAllDirFile() {

        File f = new File(getPath_File_User());
        if (!f.exists()) {
            boolean bol = f.mkdirs();
            return bol;
        }

        f = new File(getPath_File());
        if (!f.exists()) {
            boolean bol = f.mkdirs();
            return bol;
        }

        f = new File(getPath_Log());
        if (!f.exists()) {
            boolean bol = f.mkdirs();
            return bol;
        }

        f = new File(getPath_Log_User());
        if (!f.exists()) {
            boolean bol = f.mkdirs();
            return bol;
        }

        return true;
    }

    public String getPath_File() {

          return checkPathLogHome(settyng.getPath_home()) + this.path_file;
    }

    public String getPath_File_User() {

         return checkPathLogHome(settyng.getPath_home()) + this.path_file + "user" + File.separator;
    }

    public String getPath_Log() {

        return checkPathLogHome(settyng.getPath_home()) + this.path_log;
    }

    public String getPath_Log_User() {
      
    return checkPathLogHome(settyng.getPath_home()) + this.path_log + "user" + File.separator;
    }

    private String checkPathLogHome(String paths_home) {
        if (paths_home == null || paths_home.isEmpty()) {
            return this.path_home;
        }
        if (paths_home.endsWith(File.separator)) {
            return paths_home;
        } else {
            return paths_home + File.separator;
        }
    }

        private String checkLogAllName(String logName) {
        if (logName == null || logName.isEmpty()) {
            return this.logAllNameFile;
        }
        if (logName.endsWith(checkFileExtension())) {  
            return logName;
        } else {
            return logName + checkFileExtension();
        }
    }
    
    protected String checkLogAllNameUser() {
        String logName= settyng.getLogName(); 
        if (logName == null || logName.isEmpty()) {
            return this.logAllNameLog;
        }
        
        if (logName.endsWith(checkExtension())) {
            return logName;
        } else {
            return logName.split("\\.")[0] + checkExtension();
        }
    }
    
    
        protected String checkExtension() {
        String logsExtension=settyng.getLogExtension();
        if (logsExtension == null || logsExtension.isEmpty()) {
            return this.logExtension;
        }
        if (logsExtension.startsWith(".")) {
            return logsExtension;
        } else {
            return "."+logsExtension;
        }
    }

       private String checkFileExtension() {
        String logsFileExtension=settyng.getLogFileExtension();
        if (logsFileExtension == null || logsFileExtension.isEmpty()) {
            return this.logFileExtension;
        }
        if (logsFileExtension.startsWith(".")) {
            return logsFileExtension;
        } else {
            return "."+logsFileExtension;
        }
    }
           

    protected void setSettyngSerial(Settyngs settyngs){
         settyngs.settyng(settyng);
    } 
    
    
}
