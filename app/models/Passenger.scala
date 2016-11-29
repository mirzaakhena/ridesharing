package models

import play.api.libs.json.Json

/**
  * Created by mirzaakhena on 11/28/16.
  */
case class Passenger(
                      id: Int,
                      status: String // "INACTIVE" | "ACTIVE" | "WAITING" | "GORIDE"
                    )

object Passenger {
  implicit val writer = Json.writes[Passenger]
}
