package org.inria.restlet.mta.backend;

public class ResponsableBilleterie extends Thread{

   private int nombreBilletAmettre;
   private Billeterie billeterie;


    public ResponsableBilleterie(int nombreBilletAmettre, Billeterie billeterie){
        this.nombreBilletAmettre = nombreBilletAmettre;
        this.billeterie = billeterie;
    }

    public void run(){

        while (true){

            System.out.println("tour");

            try {

                billeterie.traintementResponsableBillet(nombreBilletAmettre);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
