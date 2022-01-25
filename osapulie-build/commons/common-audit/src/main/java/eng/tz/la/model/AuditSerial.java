/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  eng.tz.la.model;

import eng.tz.la.model.Userlog;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author s.mariniello
 */
public class AuditSerial extends LinkedHashMap<String, Userlog> implements Serializable{

    public AuditSerial() {
    }

    public AuditSerial(Map<? extends String, ? extends Userlog> m) {
        super(m);
    }

    @Override
    public Userlog get(Object key) {
        return super.get(key); 
    }

    @Override
    public Userlog put(String key, Userlog value) {
        return super.put(key, value); 
    }

    @Override
    public int size() {
        return super.size();  
    }
    
    
    
}
