/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.tz.la.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author s.mariniello
 */
public class Userlog extends ArrayList<Line<?>> implements Serializable{

    private String uuid;
    private String user;
    private String timeLoggin;
    private String timeLogout;

    public Userlog() {
    }
    
    public Userlog(String user) {
        this.uuid = UUID.randomUUID().toString();
        this.user = user;
        this.timeLoggin = new SimpleDateFormat("dd-MM-yyyy").format(new Date(System.currentTimeMillis()));
        this.timeLogout = "";
    }
    
        
     
    public Userlog log(Line<?> e) {
         super.add(e); 
         return this;
    }
    
    
    @Override
    public boolean add(Line<?> e) {
        return super.add(e); 
    }

    @Override
    public Line<?> get(int index) {
        return super.get(index);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimeLoggin() {
        return timeLoggin;
    }

    public void setTimeLoggin(String timeLoggin) {
        this.timeLoggin = timeLoggin;
    }

    public String getTimeLogout() {
        return timeLogout;
    }

    public void setTimeLogout(String timeLogout) {
        this.timeLogout = timeLogout;
    }
 
    
  
}
