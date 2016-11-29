package models

import play.api.libs.json.Json

/**
  * Created by mirzaakhena on 11/28/16.
  */
case class User(
                 id: Int,
                 name: String,
                 password: String,
                 role: String
               )

object User {
  implicit val writer = Json.writes[User]
}
