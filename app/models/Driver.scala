package models

import play.api.libs.json.Json

/**
  * Created by mirzaakhena on 11/28/16.
  */
case class Driver(
                   id: Int,
                   status: String, // "INACTIVE" | "ACTIVE" | "BUSY"
                   latitude: Double,
                   longitude: Double
                 )

object Driver {
  implicit val writer = Json.writes[Driver]
}
