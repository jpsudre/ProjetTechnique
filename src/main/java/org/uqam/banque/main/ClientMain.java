package org.uqam.banque.main;

import org.uqam.banque.baseDeDonnees.BaseDeDonnees;
import org.uqam.banque.commande.CommandeClient;

public class ClientMain {

    public static void main(String[] args) {

        BaseDeDonnees donnees = new BaseDeDonnees();

        if (args.length < 3) {
            System.out.println("Il faut mettre 3 arguments, de la sorte. \nExemple: ./client -n CLIENT_NAME --status\nExemple: ./client -n CLIENT_NAME --subscribe PRODUCT_ID");
            System.exit(1);
        }

        if (!args[0].equals("-n")) {
            System.out.println("Il faut que le premier argument soit = '-n'. \nExemple: ./client -n CLIENT_NAME --status\nExemple: ./client -n CLIENT_NAME --subscribe PRODUCT_ID");
            System.exit(2);
        }

        String nomClient = args[1]; // nom du client
        String typeCommande = args[2]; // --status, --subscribe, --Unsubscribe par exemple

        if (typeCommande.equals("--status")) {
            final int codeRetour = CommandeClient.statutClientParNom(donnees, nomClient);

            System.exit(codeRetour);
        } else if (typeCommande.equals("--subscribe")) {
            if (args.length < 4) {
                System.out.println("Il faut mettre 4 arguments, de la sorte. \nExemple: ./client -n CLIENT_NAME --subscribe PRODUCT_ID");
                System.exit(3);
            }

            String idProduit = args[3]; // nom du client

            final int codeRetourSouscription = CommandeClient.souscriptionProduitAuClientParNom(donnees,
                                                                                                nomClient,
                                                                                                idProduit);

            if (codeRetourSouscription != 0) {
                System.exit(codeRetourSouscription);
            }

            // affichage du statut du client
            final int codeRetourStatut = CommandeClient.statutClientParNom(donnees, nomClient);

            System.exit(codeRetourStatut);
            
        }else if (typeCommande.equals("--unsubscribe")) {
                if (args.length < 4) {
                    System.out.println("Il faut mettre 4 arguments, de la sorte. \nExemple: ./client -n CLIENT_NAME --subscribe PRODUCT_ID");
                    System.exit(3);
                }

                String idProduit = args[3]; // nom du client

                final int codeRetourunsubscribe = CommandeClient.unsubscribeProduitAuClientParNom(donnees,
                                                                                                    nomClient,
                                                                                                    idProduit);

                if (codeRetourunsubscribe != 0) {
                    System.exit(codeRetourunsubscribe);
                }

                // affichage du statut du client
                final int codeRetourStatut = CommandeClient.statutClientParNom(donnees, nomClient);

                System.exit(codeRetourStatut);
            } else {
                System.out.println("Seul --status, --subscribe et Unsubscribe sont autorises pour les commandes.");
                System.exit(10);
            }
      
        }
    
    
}
    


