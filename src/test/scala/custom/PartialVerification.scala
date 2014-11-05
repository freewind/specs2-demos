package custom

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class PartialVerification extends Specification with Mockito {

  "UserService" should {
    "show user and I only concern the age not the name" in {
      val template = mock[Template]
      val service = new UserService(template)
      val user = User("Freewind", 100)

      service.show(user)

      there was one(template).render(matchUserAge(100))
    }
  }

  private def matchUserAge(age: Int) = be_==(age) ^^ ((_: User).age)

  class UserService(template: Template) {
    def show(user: User) = template.render(user)
  }

  class Template {
    def render(user: User) = "rendered"
  }

  case class User(name: String, age: Int)

}
