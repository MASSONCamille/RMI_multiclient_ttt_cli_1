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
        int test = 0;

        JFrame fenetre = new JFrame();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(false);


        j = look_up.ReStart();
        System.out.println(j);  //ligne test console

        System.out.println(look_up.ImprGrille());

        while (test == 0) {
            if (j == look_up.getCurrentPlayer()) {
                System.out.println("actif");  //ligne test console
                int pos = -1;
                do {
                    if (pos != -1) JOptionPane.showMessageDialog(fenetre, "Erreur de saisie");
                    try {
                        pos = Integer.parseInt(JOptionPane.showInputDialog(fenetre ,"C'est votre tour J"+ j +", choisisez une case (1 à 9): ")) - 1;
                    }catch (Exception e){
                        pos = -1;
                    }
                    if (pos < 0 || pos > 9) pos = -1;
                }while (!look_up.Play(pos));

            } else look_up.WaitOtherPlayer();


            System.out.println(look_up.ImprGrille());

            test = look_up.TestFin();
            if (test == 0);
            else if (test == 3) JOptionPane.showMessageDialog(fenetre, "La partie est termine et personne ne gagne");
            else if (test == j) JOptionPane.showMessageDialog(fenetre, "Vous remportez la partie");
            else JOptionPane.showMessageDialog(fenetre, "Vous avez perdu la partie");

            look_up.NextPlayer();
            System.out.println("fin de tour");  //ligne test console
        }
        System.out.println("sortie de boucle");  //ligne test console
    }
}