# Complexity Analysis

1. Rules for computing complexity analysis:
   - Focus on the worst case, i.e. if the number of operations is between 1 and 2N+1, consider only 2N+1.
   - Ignore small inputs,  e.g., treat 2N + 1 just like 2N
   - Ignore constant scaling factor, e.g., treat 2N like N
3. **Big Theta notation**: 对于函数F(N)和函数R(N)，如果k1 * R(N) < F(N) < k2 * R(N)对N>=N0成立，则认为**F(N)∈Θ(R(N))**。（其中k1、k2为常数，N0为一个很大的值）
4. **Big O notation**: 对于函数F(N)和函数R(N)，如果 F(N) <= k2 * R(N)对N>=N0成立，则认为**F(N)∈O(R(N))**。（其中k2为常数，N0为一个很大的值)
   - For f(x) and g(x), f(x) is in the set of O(g(x)) if for some constantsa,b>0,f(x)<=a*g(x)forallx>b
4. Big Omega Notation: 对于函数F(N)和函数R(N)，如果 F(N) >= k2 * R(N)对N>=N0成立，则认为**F(N)∈Ω(R(N))**。
5. Amortized analysis

## Summary

| notation | informal meaning                       | Example family   | Example family members                               |
| -------- | -------------------------------------- | ---------------- | ---------------------------------------------------- |
| Ω(f(N))  | greater than or equal to (lower bound) | Ω(N<sup>2</sup>) | N<sup>3</sup>, 2N<sup>2</sup>                        |
| Θ(f(N))  | Equals                                 | Θ(N<sup>2</sup>) | N<sup>2</sup> + 1, N<sup>2</sup> + N, 2N<sup>2</sup> |
| O(f(N))  | less than or equal to (upper bound)    | O(N<sup>2</sup>) | N<sup>2</sup>/2, 2N<sup>2</sup>, logN, N,            |

O(N!) > O(2<sup>N</sup>) > O(N<sup>2</sup>) > O(NlogN) > O(N) > O(N<sup>1/2</sup>) > O(logN) > O(1)