## Things which have tripped me up:
1. Make sure you're answering the right questions
   1. Don't be lazy with unit tests, cover thoroughly to give the interviewing confidence
2. Finding pivot in rotated array:
   1. Think of it as finding which side the current element belongs to
   2. Keep track of the smallest element while doing this
3. twopointer.ThreeSum:
   1. On second thought, it should be possible to do 3sum with hashset. Maybe try it!
4. Avoid building hashmaps or other structures more than once:
   1. They end up being too slow
5. Backtracking:
   1. For powersets (all combinations, not all orders) suffix only needs to contain the right hand side for future iterations
   2. The "all combination of coins" question is a disguised combination sum :)
6. Dynamic Programming
   1. Recusrive solution is sometimes good. e.g. https://leetcode.com/problems/jump-game-ii/submissions/489397902/
   2. 

## Nice to Haves
1. Try recursive version of DFS
2. Is there an iterative version to backtracking algorithms?