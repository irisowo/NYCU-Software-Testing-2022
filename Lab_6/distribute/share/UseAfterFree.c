#include <stdio.h>
#include <stdlib.h>

int main(){
    int *myHeap = malloc(2);
    free(myHeap);
    int used_after_free = myHeap[0];
    return 0;
}