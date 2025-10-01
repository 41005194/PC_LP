package Java.TD1;

public class Main {
    static final int N = 50000;
    static final int N_THREADS = 8;

    public static double f(int x, int y) {
        return x * x * x * Math.sin((double)x) - y * y * y * Math.cos((double)y) + x * y * y;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();

        Resultat[] resultats = new Resultat[N_THREADS];
        Thread[] threads = new Thread[N_THREADS];

        for (int i = 0; i < N_THREADS; i++) {
            resultats[i] = new Resultat(i);
            threads[i] = new MonThread(resultats[i]);
            threads[i].start();
        }
        for (int i = 0; i < N_THREADS; i++) {
            threads[i].join();
        }

        double maxp = resultats[0].max;
        int x_maxp = resultats[0].x_max, y_maxp = resultats[0].y_max;
        for (int i = 1; i < N_THREADS; i++) {
            if (resultats[i].max > maxp) {
                maxp = resultats[i].max;
                x_maxp = resultats[i].x_max;
                y_maxp = resultats[i].y_max;
            }
        }

        System.out.printf("Le max est atteint en (x=%d, y=%d) et vaut %f\n", x_maxp, y_maxp, maxp);

        long end = System.nanoTime();
        double elapsed = (end - start) / 1e9;
        System.out.printf("Temps d'exécution : %.6f secondes\n", elapsed);
    }
}
