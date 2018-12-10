package org.inria.restlet.mta.backend;

public class Client extends Thread{

    private int nbreDeBilletAAcheter;
    private Billeterie billeterie;
    private Attraction attraction;
    private String etat;
    private int dureeAttraction;
    private int num;

    public Client(int num,int nbreDeBilletAAcheter, Billeterie billeterie, Attraction attraction) {

        this.etat = "INIT";
        this.nbreDeBilletAAcheter = nbreDeBilletAAcheter;
        this.billeterie = billeterie;
        this.attraction = attraction;
        this.dureeAttraction = 2000;
        this.num = num;

    }

    public void setState(String state) {
        this.etat = state;
    }

    public String getEtat() {
        return etat;
    }

    public int getNom() {
        return num;
    }

    public void run(){

        try {
            billeterie.acheterBillet(nbreDeBilletAAcheter,this);

            attraction.traintementClient(this);

            Thread.sleep(dureeAttraction);

            attraction.traintementClient(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
