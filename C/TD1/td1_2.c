#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <sys/wait.h>
#include <sys/time.h>

#include "td_1_calcul.c"

#define N 50000
#define N_FILS 1

int main(void) {
    struct timeval start, end;
    gettimeofday(&start, NULL);

    int fd[N_FILS][2];
    for (int i = 0; i < N_FILS; i++) {
        if (pipe(fd[i]) == -1) {
            perror("pipe");
            exit(1);
        }
    }

    double maxs[N_FILS] = {0};
    int x_maxs[N_FILS] = {0}, y_maxs[N_FILS] = {0};

    for (int i = 0; i < N_FILS; i++) {
        int k = fork();
        if (k == 0) {
            // Fils
            close(fd[i][0]);
            double maxf = 0;
            int x_maxf = 0, y_maxf = 0;
            for (int x = i; x <= N; x += N_FILS) {
                for (int y = 0; y <= N; y++) {
                    float cal = f(x, y);
                    if (fabs(cal) > maxf) {
                        maxf = cal;
                        x_maxf = x;
                        y_maxf = y;
                    }
                }
            }
            write(fd[i][1], &maxf, sizeof(double));
            write(fd[i][1], &x_maxf, sizeof(int));
            write(fd[i][1], &y_maxf, sizeof(int));
            close(fd[i][1]);
            exit(0);
        }
    }

    // Père
    for (int i = 0; i < N_FILS; i++) {
        close(fd[i][1]);
        read(fd[i][0], &maxs[i], sizeof(double));
        read(fd[i][0], &x_maxs[i], sizeof(int));
        read(fd[i][0], &y_maxs[i], sizeof(int));
        close(fd[i][0]);
    }

    double maxp = maxs[0];
    int x_maxp = x_maxs[0], y_maxp = y_maxs[0];
    for (int i = 1; i < N_FILS; i++) {
        if (maxs[i] > maxp) {
            maxp = maxs[i];
            x_maxp = x_maxs[i];
            y_maxp = y_maxs[i];
        }
    }

    printf("Le max est atteint en (x=%d, y=%d) et vaut %f\n", x_maxp, y_maxp, maxp);

    gettimeofday(&end, NULL);
    double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1e6;
    printf("Temps d'exécution : %.6f secondes\n", elapsed);

    return 0;
}
