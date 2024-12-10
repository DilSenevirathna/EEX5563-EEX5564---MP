class NextFitAllocator:
    def __init__(self, memory_blocks):
        self.memory_blocks = memory_blocks
        self.pointer = 0  # Tracks the last allocated block

    def allocate(self, process_size):
        n = len(self.memory_blocks)
        for _ in range(n):
            if self.memory_blocks[self.pointer] >= process_size:
                print(f"Process allocated {process_size} KB in Block {self.pointer + 1}")
                self.memory_blocks[self.pointer] -= process_size
                return
            self.pointer = (self.pointer + 1) % n  # Move to the next block, wrapping around
        print("Allocation failed: No suitable block found.")

    def deallocate(self, block_index, size):
        self.memory_blocks[block_index] += size
        print(f"Block {block_index + 1} deallocated with {size} KB.")

    def display_memory(self):
        for i, block in enumerate(self.memory_blocks):
            print(f"Block {i + 1}: {block} KB")


def get_algorithm_by_remainder(registration_number):
    remainder = registration_number % 6
    algorithms = [
        "First Fit",  # 0
        "Next Fit",   # 1
        "Best Fit",   # 2
        "Worst Fit",  # 3
        "Buddy System",  # 4
        "Quick Fit"   # 5
    ]
    return algorithms[remainder], remainder


if __name__ == "__main__":
    # User input for registration number
    registration_number = int(input("Enter your registration number: "))
    
    # Calculate the remainder
    algorithm, remainder = get_algorithm_by_remainder(registration_number)
    print(f"Selected Algorithm: {algorithm} (Remainder: {remainder})\n")
    
    # Initialize memory blocks for simulation
    memory_blocks = [200, 300, 100, 500, 50]
    allocator = NextFitAllocator(memory_blocks)

    print("Initial Memory Blocks:")
    allocator.display_memory()

    # Simulate memory allocation based on selected algorithm
    if algorithm == "Next Fit":
        allocator.allocate(120)  # Example allocation
        allocator.allocate(250)
        allocator.allocate(50)
        allocator.deallocate(1, 120)  # Freeing 120 KB from Block 2
    # You can add more algorithms' logic here if required

    print("\nUpdated Memory Blocks:")
    allocator.display_memory()
