package Java.TD4;

public class Main {

    public static void main(String[] args) {
            Allocateur allocateur = new Allocateur(50);
            ThreadAllocateur t1 = new ThreadAllocateur(allocateur, 10);
            ThreadAllocateur t2 = new ThreadAllocateur(allocateur, 20);
            ThreadAllocateur t3 = new ThreadAllocateur(allocateur, 30);

            t1.start();
            t2.start();
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }