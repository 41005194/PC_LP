#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <sys/time.h>
#include <pthread.h>

#include "td_1_calcul.c"

#define N 50000
#define N_FILS 8

typedef struct {
    double max;
    int x_max, y_max;
    int i_thread;
} Resultat;

void* cherche_max(void* arg) {
    Resultat* res = (Resultat*)arg;
    printf("Thread %d démarre\n", res->i_thread);
    res->max = 0;
    res->x_max = 0;
    res->y_max = 0;
    int i = res->i_thread;
    for (int x = i; x <= N; x += N_FILS) {
        for (int y = 0; y <= N; y++) {
            float cal = f(x, y);
            if (fabs(cal) > res->max) {
                res->max = cal;
                res->x_max = x;
                res->y_max = y;
            }
        }
    }
    printf("Thread %d termine\n", res->i_thread);
    return NULL;
}

int main(void){
    struct timeval start, end;
    gettimeofday(&start, NULL);

    pthread_t threads[N_FILS];
    Resultat resultats[N_FILS];

    for (int i = 0; i < N_FILS; i++) {
        resultats[i].i_thread = i;
        pthread_create(&threads[i], NULL, cherche_max, &resultats[i]);
    }
    for (int i = 0; i < N_FILS; i++) {
        pthread_join(threads[i], NULL);
    }


    double maxp = resultats[0].max;
    int x_maxp = resultats[0].x_max, y_maxp = resultats[0].y_max;
    for (int i = 1; i < N_FILS; i++) {
        if (resultats[i].max > maxp) {
            maxp = resultats[i].max;
            x_maxp = resultats[i].x_max;
            y_maxp = resultats[i].y_max;
        }
    }

    printf("Le max est atteint en (x=%d, y=%d) et vaut %f\n", x_maxp, y_maxp, maxp);

    gettimeofday(&end, NULL);
    double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1e6;
    printf("Temps d'exécution : %.6f secondes\n", elapsed);

    return 0;
}