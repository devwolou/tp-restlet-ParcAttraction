package org.inria.restlet.mta.backend;

public class Attraction {


        private int nombreDePlaceDispoNavette;
        private boolean navettePresent,arriveAttraction;


    public Attraction() {
        this.nombreDePlaceDispoNavette = 5;
        this.navettePresent = false;
        this.arriveAttraction = false;
    }

    public synchronized void traintementClient(Client client) throws InterruptedException {

        while (nombreDePlaceDispoNavette== 0 & navettePresent){

            System.out.println("Client: "+client.getNom()+" attend la Navette ou il y a plus de place");
            wait();
        }

        notifyAll();
        System.out.println("Client: "+client.getNom()+" est dans la Navette");
        nombreDePlaceDispoNavette--;

        if (client.getEtat()=="INIT"){
            client.setState("ENTERED");
        }else {
            client.setState("TRANSIT");
        }


        while (!arriveAttraction){
            System.out.println("Client: "+client.getNom()+" est en attente d'arrivée à une attraction");
            wait();
        }

        System.out.println("Client: "+client.getNom()+" est dans une attraction");

        if(client.getEtat() == "TRANSIT"){
           client.setState("RIDE2");
        }else if(client.getEtat()=="ENTERED") {
            client.setState("RIDE1");
        }

    }

    public synchronized void GareNavette(Navette navette) throws InterruptedException {


        while (navettePresent){

            System.out.println("Navette: "+ navette.getNum()+" attend le depart d'une autre navette");
            wait();
        }

        arriveAttraction = false;

        System.out.println("Navette: "+ navette.getNum()+ " à quai");

        navette.setEtatNavette("STOPPED");

        navettePresent = true;
        nombreDePlaceDispoNavette = 5;
        notifyAll();

    }

    public synchronized void departNavette(Navette navette){


        if(navette.getEtatNavette() == "STOPPED"){
            navette.setEtatNavette("TRAVELLING");
        }

        System.out.println("Navette: "+ navette.getNum()+ " est partie");
        navettePresent = false;

        notifyAll();
    }

    public synchronized void finTrajetNavette(){

        arriveAttraction = true;
        notifyAll();
    }

}
