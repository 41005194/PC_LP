package Java.TD4;

public class Allocateur {
    private byte[] memoire;
    private boolean[] blocsUtilises;

    public Allocateur(int taille) {
        memoire = new byte[taille];
        blocsUtilises = new boolean[taille];
    }

    public int allouer(int taille) {
        for (int i = 0; i <= memoire.length - taille; i++) {
            boolean libre = true;
            for (int j = 0; j < taille; j++) {
                if (blocsUtilises[i + j]) {
                    libre = false;
                    break;
                }
            }
            if (libre) {
                for (int j = 0; j < taille; j++) {
                    blocsUtilises[i + j] = true;
                }
                return i;
            }
        }
        return -1;
    }

    public void liberer(int adresse, int taille) {
        for (int i = 0; i < taille; i++) {
            blocsUtilises[adresse + i] = false;
        }
    }
}