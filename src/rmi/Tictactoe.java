package rmi;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Tictactoe {
    private static RMI_tictactoe look_up;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        try {
            int port = 5000;
            look_up = (RMI_tictactoe) Naming.lookup("rmi://localhost:" + port + "/tictactoe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        int j;
        boolean fin;
        int test;

        JFrame fenetre = new JFrame();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(false);


        j = look_up.ReStart();
        System.out.println(j);  //ligne test console
        fin = false;

        System.out.println(look_up.ImprGrille());

        while (!fin) {
            if (j == look_up.getCurrentPlayer()) {
                System.out.println("actif");  //ligne test console
                int pos = Integer.parseInt(JOptionPane.showInputDialog(fenetre ,"C'est votre tour J"+ j +", choisisez une case (1 à 9): ")) - 1;
                look_up.Play(pos);
            } else look_up.WaitOtherPlayer();


            System.out.println(look_up.ImprGrille());

            fin = true;
            test = look_up.TestFin();
            if (test == 0) fin = false;
            else if (test == 3) JOptionPane.showMessageDialog(fenetre, "La partie ce termine et personne ne gagne");
            else if (test == j) JOptionPane.showMessageDialog(fenetre, "Vous remporter la partie");
            else JOptionPane.showMessageDialog(fenetre, "Vous avez perdu la partie");

            look_up.NextPlayer();
        }

    }
}