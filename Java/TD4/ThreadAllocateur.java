package Java.TD4;

public class ThreadAllocateur extends Thread {
    private Allocateur allocateur;
    private int taille;

    public ThreadAllocateur(Allocateur allocateur, int taille) {
        this.allocateur = allocateur;
        this.taille = taille;
    }

    @Override
    public void run() {
        synchronized(allocateur) {
            int adresse = allocateur.allouer(taille);
            if (adresse != -1) {
                System.out.println("Alloué " + taille + " octets à l'adresse " + adresse);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                allocateur.liberer(adresse, taille);
                System.out.println("Libéré " + taille + " octets à l'adresse " + adresse);
            } else {
                System.out.println("Échec de l'allocation de " + taille + " octets");
            }
        }
    }
}