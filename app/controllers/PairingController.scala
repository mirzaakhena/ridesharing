package controllers

import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.mvc._

class PairingController @Inject() () extends Controller {

  def test = Action.async {
    r=> {
      scala.concurrent.Future {
        println("do something here")
      }.map(i => Ok(Json.obj("message" -> "Mantap sekalee!!")))
    }
  }

  def sendDriverLocation = TODO
  
}