package Java.TD3;

public class ThreadMoins extends Thread {
    private int decr;
    private Valeur valeur;

    public ThreadMoins(int decr, Valeur valeur) {
        this.decr = decr;
        this.valeur = valeur;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (this.valeur) {
                valeur.x -= decr;
            }
        }
    }
}
