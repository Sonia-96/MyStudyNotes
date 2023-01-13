# Dynamic Programming

Dynamic Programming: a divide-and-conquere strategy. Divide the problem into subproblems and solve them recursively.

- Memoization: store the results of subproblems and reuse them. Usually we use hashtable, of which the key is the arguments passed to the subproblem, value is the return value.
- solve the smallest subproblems first, then choose subproblem order such that I already have needed results to solve bigger problems. -> design a "table" to store subproblem results, then pick an order to fill the table.

## Question 1

