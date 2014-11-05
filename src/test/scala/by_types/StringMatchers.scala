package by_types

import java.util.regex.Pattern

import org.specs2.mutable.Specification
import java.util.regex.Pattern

// refer to:
// https://github.com/etorreborre/specs2/blob/master/tests/src/test/scala/org/specs2/matcher/StringMatchersSpec.scala
class StringMatchers extends Specification {

  "String" should {
    "have basic matchers" in {
      "Hello" === "Hello"
      "Hello" must be("Hello")
      "Hello" must beEqualTo("Hello")
      "Hello" must be equalTo "Hello"

      "Hello" must have size 5
      "Hello" must have length 5

      "Hello" must startWith("He")
      "Hello" must be startingWith "He"

      "Hello" must endWith("lo")
      "Hello" must be endingWith "lo"
    }

    "match with regex" in {
      "Hello" must beMatching( """\w+""")
      "Hello" must beMatching( """\w+""".r)
      "Hello" must beMatching(Pattern.compile( """\w+"""))
      "Hello\nworld" must beMatching(Pattern.compile( """^.+$""", Pattern.DOTALL))
      "Hello" must be matching """\w+"""

      "Hello" must =~( """\w+""")
      "Hello" must be =~ """\w+"""
      "Hello" must be =~ """\w+""".r
      "Hello" must be =~ Pattern.compile( """\w+""")
      "Hello\nworld" must be =~ Pattern.compile( """^.+$""", Pattern.DOTALL)

      "Hello" aka "world" must be matching "H.*"

      // The string you want to capture must be inside the `()`
      "Hello" must find( """(\w)""").withGroups("H", "e", "l", "l", "o")
      "Hello" must find( """(\w)\w""").withGroups("H", "l")
    }
  }

}
