package cc.kamma

package object nowaterscala {
  type Definitions = Seq[(String, Seq[(String, String)])]

  def expand(d: Definitions): Definitions = {

    def noMixin(d: Definitions): Boolean = {
      d.forall {
        case (key, value) => value.forall {
          case (innerKey, _) => innerKey != "mixin"
        }
      }
    }

    if (noMixin(d))
      d
    else {
      expand(d.map {
        case (key, value) =>
          val newValue: Seq[(String, String)] = value.flatMap {
            case (innerKey: String, innerValue: String) if innerKey == "mixin" => d.toMap.apply(innerValue)
            case x: (String, String) => Seq(x)
          }
          (key, newValue)
      })
    }


  }

}
