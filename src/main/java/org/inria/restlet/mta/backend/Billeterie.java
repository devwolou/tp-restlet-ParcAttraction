package org.inria.restlet.mta.backend;

public class Billeterie {

         private int nombreDeBillet;
         private boolean prevenirResponsable;



    public Billeterie(int nombreDeBillet) {
        this.nombreDeBillet = nombreDeBillet;
        prevenirResponsable = false;
    }

    public synchronized void acheterBillet(int nbrBillet,Client client) throws InterruptedException {

        while (nbrBillet > nombreDeBillet){

            System.out.println("Responsable billetterie au secours!" + " " + nombreDeBillet);
            prevenirResponsable = true;

            Thread.sleep(50);

            notifyAll();
            wait();
        }

        nombreDeBillet -= nbrBillet;
        System.out.println("Client: "+client.getNom()+" Billet Acheté, en attente d'impression de billet");

        Thread.sleep(50);
        System.out.println("Client: "+client.getNom()+" J'ai mon billet");

    }

    public synchronized void traintementResponsableBillet(int billetAAjouter) throws InterruptedException
    {
        System.out.println("respo present");

        while (!prevenirResponsable){

            System.out.println("wait Respo");
            wait();
            System.out.println("respo reveillé");
        }

        nombreDeBillet += billetAAjouter;

        System.out.println("Responsable billetterie a bien rechargé");

        prevenirResponsable = false;
        notifyAll();
    }


}
