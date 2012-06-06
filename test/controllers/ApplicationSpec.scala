package controllers;
import org.specs2.mutable._

class ApplicationSpec extends Specification {
  "The Application controller" should {
    "map id 1 correct" in {
      Application.getName(1) must_== "Steve"
    }
	"map id 2 correct" in {
	  Application.getName(2) must_== "Bill"
    }
  }
}