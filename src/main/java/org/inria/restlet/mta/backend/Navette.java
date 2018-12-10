package org.inria.restlet.mta.backend;

public class Navette extends Thread {

    Attraction attraction;

    private int dureeTrajet;
    private int dureeAttenteQuai;
    private String etatNavette;
    private int num;


    public Navette(int num,Attraction attraction){
        this.etatNavette = "STOPPED";
        this.attraction = attraction;
        this.dureeTrajet = 2000;
        this.dureeAttenteQuai = 1000;
        this.num = num;

    }

    public String getEtatNavette() {
        return etatNavette;
    }

    public void setEtatNavette(String etatNavette) {
        this.etatNavette = etatNavette;
    }

    public int getNum() {
        return num;
    }

    @Override
    public void run() {

        while (true) {

            try {
                attraction.GareNavette(this);

                Thread.sleep(dureeAttenteQuai);

                attraction.departNavette(this);

                Thread.sleep(dureeTrajet);

                attraction.finTrajetNavette();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
