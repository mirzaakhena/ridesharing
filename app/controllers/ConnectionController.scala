package controllers

import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.mvc._
import services.ConnectionService

import scala.concurrent.Future

class ConnectionController @Inject()(connService: ConnectionService) extends Controller {

  case class LoginDto(email:String, password:String)

  object LoginDto {
    implicit val writer = Json.format[LoginDto]
  }

  def login = Action.async(BodyParsers.parse.json) {
    request => {
      val userLoginRequest = request.body.validate[LoginDto]

      userLoginRequest.isSuccess match {
        case false =>
          Future(BadRequest(Json.obj("message" -> "Parsing input failed")))
        case true =>
          connService.login(userLoginRequest.asOpt.get.email, userLoginRequest.asOpt.get.password).map {
            jwtToken =>
              if (jwtToken.isEmpty){
                Status(401)(Json.obj("Status" -> "Invalid Authorization"))
              } else {
                Ok(Json.obj("message" -> "Success Login", "jwt_Token" -> jwtToken))
              }
          }
      }

    }
  }

  def testToken = Action.async(parse.json) { request =>
    implicit val tokenReader = Json.reads[TokenRequest]
    request.body.validate[TokenRequest].map({ tokenRequest =>
      val claims = connService.extractToken(tokenRequest.token)
      Future.successful(Ok(Json.obj("claims" -> JsString(claims))))
    }).recoverTotal( e => Future.successful(BadRequest()))
  }

  def showAll = Action.async {
    connService.showAll.map(u=>{
      Ok(Json.toJson(u))
    })
  }

}

case class TokenRequest(token: String)
