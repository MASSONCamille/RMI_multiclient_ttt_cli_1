package test;

import RMI.RMIInterface;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Test {
    private static TestInterface look_up;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        try {
            int port = 8000;
            look_up = (TestInterface) Naming.lookup("rmi://localhost:" + port + "/test");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

//        System.out.println(look_up.InternTest());
        System.out.println(look_up.getOtherExcept());
    }

}
