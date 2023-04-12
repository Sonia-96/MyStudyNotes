# const char * const p

the first `const` is for `char`, the second `const` if for the pointer `p`. ([ref](https://stackoverflow.com/questions/4949254/const-char-const-versus-const-char))

1. Mutable pointer to a mutable character

   ```cpp
   char *p;
   ```

2. Mutable pointer to a constant character

   ```cpp
   const char *p;
   ```

3. Constant pointer to a mutable character

   ```cpp
   char * const p; 
   ```

4. Constant pointer to a constant character

   ```cpp
   const char * const p;
   ```