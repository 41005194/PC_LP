package Java.TD3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int erreurs = 0;
        int essais = 1000;
        for (int essai = 0; essai < essais; essai++) {
            Valeur valeur = new Valeur(0);
            ThreadPlus threadPlus = new ThreadPlus(1, valeur);
            ThreadMoins threadMoins = new ThreadMoins(1, valeur);
            threadPlus.start();
            threadMoins.start();
            threadPlus.join();
            threadMoins.join();
            if (valeur.x != 0) {
                erreurs++;
                System.out.printf("Essai %d : valeur finale = %d\n", essai, valeur.x);
            }
        }
        System.out.printf("Nombre d'erreurs (valeur finale != 0) sur %d essais : %d\n", essais, erreurs);
    }
}
