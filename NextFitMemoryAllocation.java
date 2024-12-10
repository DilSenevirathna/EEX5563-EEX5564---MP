public class NextFitMemoryAllocation {
    private int[] memoryBlocks = {200, 300, 100, 500, 50};
    private int pointer = 0; // Pointer for Next Fit algorithm

    public void allocate(int processSize) {
        int n = memoryBlocks.length;
        for (int i = 0; i < n; i++) {
            if (memoryBlocks[pointer] >= processSize) {
                System.out.println("Process allocated " + processSize + " KB in Block " + (pointer + 1));
                memoryBlocks[pointer] -= processSize;
                return;
            }
            pointer = (pointer + 1) % n;  // Move to the next block, wrapping around
        }
        System.out.println("Allocation failed: No suitable block found.");
    }

    public void deallocate(int blockIndex, int size) {
        memoryBlocks[blockIndex] += size;
        System.out.println("Block " + (blockIndex + 1) + " deallocated with " + size + " KB.");
    }

    public void displayMemory() {
        for (int i = 0; i < memoryBlocks.length; i++) {
            System.out.println("Block " + (i + 1) + ": " + memoryBlocks[i] + " KB");
        }
    }

    public static void main(String[] args) {
        NextFitMemoryAllocation allocator = new NextFitMemoryAllocation();

        System.out.println("Initial Memory Blocks:");
        allocator.displayMemory();

        // Simulate memory allocation
        allocator.allocate(120);  // Example allocation
        allocator.allocate(250);
        allocator.allocate(50);

        // Simulate deallocation
        allocator.deallocate(1, 120);  // Freeing 120 KB from Block 2

        System.out.println("\nUpdated Memory Blocks:");
        allocator.displayMemory();
    }
}
