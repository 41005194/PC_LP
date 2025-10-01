package Java.TD1;

public class MonThread extends Thread {
    private Resultat resultat;

    public MonThread(Resultat resultat) {
        this.resultat = resultat;
    }

    @Override
    public void run() {
        System.out.printf("Thread %d démarre\n", resultat.i_thread);
        for (int x = resultat.i_thread; x <= Main.N; x += Main.N_THREADS) {
            for (int y = 0; y <= Main.N; y++) {
                double cal = Main.f(x, y);
                if (Math.abs(cal) > resultat.max) {
                    resultat.max = cal;
                    resultat.x_max = x;
                    resultat.y_max = y;
                }
            }
        }
        System.out.printf("Thread %d termine\n", resultat.i_thread);
    }
}
