# 1 Intro

1. Operating System: an interface between users and hardware. "The operating system is just another program."
2. What we will learn in this course:
   - Concurrency & concurrent proramming
   - Resource management
   - Performance engineering
   - Security policy design & enforcement
   - Enduring ideas
   - Interface design

# 2 CPU Architecture



# 3 Programs & Instructions

1. specifying a program and its instructions
   - highest level - High-level programming language
   - middle level - assembly: mnemonic form
   - lowest level - machine language: bits

## ISA

Instruction Set Architecture (ISA). 

1. Two types, both are in vogue today: 
   - Complex Instruction Set Computer (CISC)
     - variable instruction sizes (instruction can have variable sizez)
     - semantically more useful
   - Reduced ISA (RISC)
     - semantically more basic
     - simpler, faster, easier to design CPU
1. decoder
   - Modern x86 architecture - CISC, Intel CPU - RISC. We use decoder to bridge the gap
   - decoder take CISC instructions and reduce them down to RISC instructions

## Assembly

1. Common assembly instructions:
   - load: load a value from RAM into a register
   - load direct: 
   - store: copy value
   - add :
     - add is a variable-sized instructions
   - Compare