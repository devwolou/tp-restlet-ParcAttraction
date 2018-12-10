package org.inria.restlet.mta.backend;

import java.util.Random;

public class SimulationParc {

    static final int maxClients = 10;
    static final int nombreBilletAmettre = 10;
    int billetDispo= 20;
    static final  int billetRandom = 3;

    private Client[] clients = new Client[maxClients];
    private int nbClients = 0;
    private Navette[] navettes = new Navette[2];
    private int nbrNavette = 0;
    private ResponsableBilleterie responsableBilleterie;



    Billeterie billeterie = new Billeterie(billetDispo);

    Attraction attraction = new Attraction();

    private  boolean nouveauClient(int numClient) {

        Random random = new Random();
        int i = 1+ random.nextInt(billetRandom);
        System.out.println(i);

        clients[nbClients] = new Client(numClient,i,billeterie, attraction);
        nbClients++;

        return true;

    }

    private void nouvelleNavette(int i){


        navettes[nbrNavette] = new Navette(i,attraction);
        nbrNavette++;
    }

    public SimulationParc() {

        int i;

        /* Instanciation des clients */

        for(i = 0; i < maxClients; i++) {
            nouveauClient(i);
        }

        /* Instanciation du Responsable billeterie */
        responsableBilleterie = new ResponsableBilleterie(nombreBilletAmettre, billeterie);
        responsableBilleterie.setDaemon(true);

        for(i= 0; i<2; i++){

            nouvelleNavette(i);
        }

    }

    public void fonctionner(){

        int i;

        for(i = 0; i< clients.length; i++){
            clients[i].start();
        }

        responsableBilleterie.start();

        for(i = 0; i< 2; i++) {
            navettes[i].setDaemon(true);
            navettes[i].start();
        }
    }

    public Client[] getClients() {
        return clients;
    }

    public Navette[] getNavettes() {
        return navettes;
    }
}
