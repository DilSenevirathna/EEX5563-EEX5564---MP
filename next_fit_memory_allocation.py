# Python implementation of the Next Fit Memory Allocation algorithm
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


if __name__ == "__main__":
    memory_blocks = [200, 300, 100, 500, 50]
    allocator = NextFitAllocator(memory_blocks)

    print("Initial Memory Blocks:")
    allocator.display_memory()

    # Simulate memory allocation
    allocator.allocate(120)  # Example allocation
    allocator.allocate(250)
    allocator.allocate(50)

    # Simulate deallocation
    allocator.deallocate(1, 120)  # Freeing 120 KB from Block 2

    print("\nUpdated Memory Blocks:")
    allocator.display_memory()
