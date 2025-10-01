package Java.TD2;

public class ThreadMoins extends Thread {
    private int decr;
    private Valeur valeur;

    public ThreadMoins(int decr, Valeur valeur) {
        this.decr = decr;
        this.valeur = valeur;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++) {
            valeur.x -= decr;
            System.out.printf("Thread -%d: %d\n", decr, valeur.x);
        }
    }
}
