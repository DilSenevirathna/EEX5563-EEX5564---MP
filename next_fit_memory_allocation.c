#include <stdio.h>

void allocate(int memory_blocks[], int block_count, int process_size, int *pointer) {
    for (int i = 0; i < block_count; i++) {
        if (memory_blocks[*pointer] >= process_size) {
            printf("Process allocated %d KB in Block %d\n", process_size, *pointer + 1);
            memory_blocks[*pointer] -= process_size;
            return;
        }
        *pointer = (*pointer + 1) % block_count;  // Move to the next block, wrapping around
    }
    printf("Allocation failed: No suitable block found.\n");
}

void deallocate(int memory_blocks[], int block_index, int size) {
    memory_blocks[block_index] += size;
    printf("Block %d deallocated with %d KB.\n", block_index + 1, size);
}

void display_memory(int memory_blocks[], int block_count) {
    for (int i = 0; i < block_count; i++) {
        printf("Block %d: %d KB\n", i + 1, memory_blocks[i]);
    }
}

int main() {
    int memory_blocks[] = {200, 300, 100, 500, 50};
    int block_count = 5;
    int pointer = 0;  // Pointer for Next Fit algorithm

    printf("Initial Memory Blocks:\n");
    display_memory(memory_blocks, block_count);

    // Simulate memory allocation
    allocate(memory_blocks, block_count, 120, &pointer);  // Example allocation
    allocate(memory_blocks, block_count, 250, &pointer);
    allocate(memory_blocks, block_count, 50, &pointer);

    // Simulate deallocation
    deallocate(memory_blocks, 1, 120);  // Freeing 120 KB from Block 2

    printf("\nUpdated Memory Blocks:\n");
    display_memory(memory_blocks, block_count);

    return 0;
}
