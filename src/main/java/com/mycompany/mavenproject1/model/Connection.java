/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.model;

/**
 *
 * @author espin
 */
public class Connection {
    private String host;
    private String db;
    private String user;
    private String password;

    public Connection(String host, String db, String user, String password) {
        this.host = host;
        this.db = db;
        this.user = user;
        this.password = password;
    }

    public Connection() {
        this("","","","");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Connection{" + "host=" + host + ", db=" + db + ", user=" + user + ", password=" + password + '}';
    }

    public void loadDataXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
