import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        // Get user input for registration number
        System.out.print("Enter your registration number: ");
        int registrationNumber = scanner.nextInt();

        // Calculate remainder to select the algorithm
        int remainder = registrationNumber % 6;
        String algorithm = "";
        switch (remainder) {
            case 0: algorithm = "First Fit"; break;
            case 1: algorithm = "Next Fit"; break;
            case 2: algorithm = "Best Fit"; break;
            case 3: algorithm = "Worst Fit"; break;
            case 4: algorithm = "Buddy System"; break;
            case 5: algorithm = "Quick Fit"; break;
        }

        System.out.println("Selected Algorithm: " + algorithm + " (Remainder: " + remainder + ")\n");

        // Initialize the memory allocator
        NextFitMemoryAllocation allocator = new NextFitMemoryAllocation();

        System.out.println("Initial Memory Blocks:");
        allocator.displayMemory();

        // Simulate memory allocation based on selected algorithm
        if ("Next Fit".equals(algorithm)) {
            allocator.allocate(120);  // Example allocation
            allocator.allocate(250);
            allocator.allocate(50);
            allocator.deallocate(1, 120);  // Freeing 120 KB from Block 2
        }

        System.out.println("\nUpdated Memory Blocks:");
        allocator.displayMemory();

        scanner.close();
    }
}
