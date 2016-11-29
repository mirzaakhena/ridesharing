package models

import play.api.libs.json.Json

/**
  * Created by mirzaakhena on 11/28/16.
  */
case class Trip(
                 id: Int,
                 id_passenger: Int,
                 id_driver: Int,
                 status: String, // "REQUESTING" | "APPROACHING" | "ONTRIP" | "FINISHED"
                 latitude_start: Double,
                 longitude_start: Double,
                 latitude_end: Double,
                 longitude_end: Double
               )

object Trip {
  implicit val writer = Json.writes[Trip]
}