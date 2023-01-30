package stacks

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given two strings s and t, return true if they are equal when both are typed into empty text editors.
'#' means a backspace character.
Note that after backspacing an empty text, the text will continue empty.
 */
class BackspaceStringCompare {
    fun backspaceCompare(s: String, t: String): Boolean {
        return evaluate(s) == evaluate(t)
    }

    private fun evaluate(input: String): String {
        val stack = ArrayDeque<Char>()
        for (character in input) {
            if (character != '#') {
                stack.addLast(character)
            } else if (stack.isNotEmpty()){
                stack.removeLast()
            }
        }
        return stack.toString()
    }

    @Test
    fun `returns true when equal strings are passed`() {
        Assertions.assertTrue(backspaceCompare("abcde", "abcde"))
    }

    @Test
    fun `returns false when non-equal strings are passed`() {
        Assertions.assertFalse(backspaceCompare("abcde8", "abcde"))
    }

    @Test
    fun `returns true when strings are equal after backspaces`() {
        Assertions.assertTrue(backspaceCompare("abcde#f", "abcdf"))
    }

    @Test
    fun `returns false when strings are non-equal after backspaces`() {
        Assertions.assertTrue(backspaceCompare("abcde#8", "abcd8"))
    }

    @Test
    fun `backspaces on empty strings are allowed`() {
        Assertions.assertTrue(backspaceCompare("ab########cd#e", "######aa#b##cc#efgh###"))
    }
}