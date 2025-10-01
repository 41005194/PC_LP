package Java.TD3;

public class ThreadPlus extends Thread {
    private int incr;
    private Valeur valeur;

    public ThreadPlus(int incr, Valeur valeur) {
        this.incr = incr;
        this.valeur = valeur;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (this.valeur) {
                valeur.x += incr;
            }
        }
    }
}
