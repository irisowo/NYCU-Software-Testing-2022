#include <stdlib.h>
#include <stdio.h>

int main(){
    char *myHeap = malloc(4);
    printf("%c\n", myHeap[4]);
    free(myHeap);

    return 0;
}