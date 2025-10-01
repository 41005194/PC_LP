package Java.TD2;

public class Main {
    public static void main(String[] args) {
        Valeur valeur = new Valeur(0);
        ThreadPlus t1 = new ThreadPlus(1, valeur);
        ThreadMoins t2 = new ThreadMoins(1, valeur);
        t1.start();
        t2.start();
    }
}
