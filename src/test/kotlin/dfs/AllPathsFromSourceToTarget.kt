package dfs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
(i.e., there is a directed edge from node i to node graph[i][j]).
 */
class AllPathsFromSourceToTarget {
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val n = graph.size - 1
        val allPaths = mutableListOf<List<Int>>()
        val stack = ArrayDeque<List<Int>>()
        stack.addLast(listOf(0))

        while (stack.isNotEmpty()) {
            val nodeChain = stack.removeLast()
            if (nodeChain.last() == n) {
                allPaths.add(nodeChain)
                continue
            }
            for (nextNode in graph[nodeChain.last()]) {
                stack.addLast(nodeChain + nextNode)
            }
        }
        return allPaths
    }
    
    @Test
    fun `((1,2),(3),(3),())`() {
        val result = allPathsSourceTarget(
            listOf(
                listOf(1, 2),
                listOf(3),
                listOf(3),
                emptyList(),
            ).map { it.toIntArray() }.toTypedArray()
        )
        Assertions.assertEquals(0, result[0][0])
        Assertions.assertEquals(2, result[0][1])
        Assertions.assertEquals(3, result[0][2])

        Assertions.assertEquals(0, result[1][0])
        Assertions.assertEquals(1, result[1][1])
        Assertions.assertEquals(3, result[1][2])
    }
}