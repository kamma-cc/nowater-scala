package cc.kamma.nowaterscala

import org.scalatest.FunSuite

class packageTest extends FunSuite {

  test("testExpand") {
    val defs: Definitions = Seq("User" -> Seq("name" -> "oldpig", "gender" -> "male", "mixin" -> "Address"),
      "Address" -> Seq("Province" -> "Shanghai", "District" -> "Minhang"))
    assert(expand(defs) == Seq("User" -> Seq("name" -> "oldpig", "gender" -> "male", "Province" -> "Shanghai", "District" -> "Minhang"),
      "Address" -> Seq("Province" -> "Shanghai", "District" -> "Minhang")))
  }
  test("testExpand2") {
    val defs: Definitions = Seq("User" -> Seq("name" -> "oldpig", "gender" -> "male", "mixin" -> "Address"),
      "Address" -> Seq("Province" -> "Shanghai", "District" -> "Minhang", "mixin" -> "Building"),
      "Building" -> Seq("BuildingName" -> "TangChenYiPin", "Height" -> "300M")
    )
    assert(expand(defs) == Seq("User" -> Seq("name" -> "oldpig", "gender" -> "male", "Province" -> "Shanghai", "District" -> "Minhang", "BuildingName" -> "TangChenYiPin", "Height" -> "300M"),
      "Address" -> Seq("Province" -> "Shanghai", "District" -> "Minhang", "BuildingName" -> "TangChenYiPin", "Height" -> "300M"), "Building" -> Seq("BuildingName" -> "TangChenYiPin", "Height" -> "300M")))
  }

}
