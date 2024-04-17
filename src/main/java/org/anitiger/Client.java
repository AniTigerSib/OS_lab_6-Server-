package org.anitiger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Client {
    private int ID;
    public Socket            socket    = null;
    public DataInputStream   in        = null;
    public DataOutputStream  out       = null;

    public Client(Socket socket, int id) {
        this.socket = socket;
        this.ID = id;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public DataInputStream getInputStream() {
        return this.in;
    }
    public DataOutputStream getOutputStream() {
        return this.out;
    }
    public int getID() {
        return this.ID;
    }
    public void close() {
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
