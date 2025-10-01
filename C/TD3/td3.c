#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <semaphore.h>
#include <pthread.h>

#define N 50000
#define n 1000

int val;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void inc(){
    for(int i=0 ; i<N ; i++){
        pthread_mutex_lock(&mutex);
        val++;
        pthread_mutex_unlock(&mutex);
    }
}

void dec(){
    for(int i=0 ; i<N ; i++){
        pthread_mutex_lock(&mutex);
        val--;
        pthread_mutex_unlock(&mutex);
    }
}

int main() {
    int erreurs = 0;
    for (int essai = 1; essai <= n; essai++) {
        val = 0;
        pthread_t t1, t2;
        pthread_create(&t1, NULL, (void *)inc, NULL);
        pthread_create(&t2, NULL, (void *)dec, NULL);
        pthread_join(t1, NULL);
        pthread_join(t2, NULL);
        if (val != 0) {
            erreurs++;
        }
        printf("Essai %d : valeur finale = %d\n", essai, val);
    }
    printf("Nombre d'erreurs (val != 0) sur %d essais : %d\n", n, erreurs);
    return 0;
}