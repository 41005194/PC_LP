package Java.TD2;

public class ThreadPlus extends Thread {
    private int incr;
    private Valeur valeur;

    public ThreadPlus(int incr, Valeur valeur) {
        this.incr = incr;
        this.valeur = valeur;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++) {
            valeur.x += incr;
            System.out.printf("Thread +%d: %d\n", incr, valeur.x);
        }
    }
}
