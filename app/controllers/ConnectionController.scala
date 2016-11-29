package controllers

import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.mvc._
import services.ConnectionService

import scala.concurrent.Future

class ConnectionController @Inject()(connService: ConnectionService) extends Controller {

  def login = Action.async {
    connService.login("", "").map(x => Ok(Json.obj("token" -> x)))
  }

  def showAll = Action.async {
    connService.showAll.map(u=>{
      Ok(Json.toJson(u))
    })
  }

}