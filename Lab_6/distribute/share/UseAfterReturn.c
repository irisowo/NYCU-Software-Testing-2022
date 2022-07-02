char* x;

void foo() {
    char stack_buffer[10];
    x = &stack_buffer[5];
}

int main() {
    foo();
    *x = 88; // Boom!
    return 0;
}