int main(){
    int a[8] = {0};
    int b[8] = {0};

    // fail to detect
    int access = a[16];
    return 0;
    
    // successfully detected
    // int access = a[15];
    // 1-7 (8-15) 16-23 (24-31)
}