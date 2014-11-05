package custom

import java.io.File

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.specs2.matcher.{Expectable, Matcher}
import org.specs2.mutable.Specification
import java.util.{Iterator => JIterator}
import scala.collection.JavaConversions._

class CustomMatchers extends Specification {

  "sdf" should {
    "sdf" in {
      val html = """<span data-desc="xxx" ddd='www'>aaa</span>"""
      html must containHtmlTag(tagName = Some("span"), text = Some("aaa"), attrs = Map("data-desc" -> "xxx"))
    }
    "should have two attributes" in {
      val html = """<div data-desc2="abc"><span data-desc='xxx' ddd='www' data-desc2="abc" class="cc">aaa</span></div>"""
      html must containHtmlTag(tagName = Some("span"), text = Some("aaa"), attrs = Map("data-desc" -> "xxx", "data-desc2" -> "abc"))
    }
  }

  def beBetween(i: Int, j: Int) = be_>=(i) and be_<=(j)

  def haveExtension(extension: => String) = ((_: File).getPath) ^^ endWith(extension)

  def containHtmlTag(tagName: Option[String] = None, text: Option[String] = None, attrs: Map[String, String]) = new Matcher[String] {
    def apply[S <: String](b: Expectable[S]) = {
      val html = b.value
      val selector = (tagName.toList ::: attrs.map({ case (k, v) => s"[$k=$v]"}).toList).mkString
      val doc = Jsoup.parse(html)

      val foundElements = doc.select(selector).listIterator().asInstanceOf[JIterator[Element]].toList
      val matched = (foundElements, text) match {
        case (Nil, _) => false
        case (_, Some(v)) => foundElements.exists(_.text() == v)
        case (_, None) => true
      }

      result(matched, "expect html tag found", "expect html tag not found", b)
    }
  }

}

object Main extends App {
  val html =
    """
       <span a1="v1" a2="v2">xxx</span>
    """

  val doc = Jsoup.parse(html)
  val e = doc.select("span[a1=v1][a2=v2][a3=v3]")
  println(e)
}
