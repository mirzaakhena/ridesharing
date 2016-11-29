package services

import models.User

import scala.concurrent.Future

/**
  * Created by mirzaakhena on 11/28/16.
  */
trait ConnectionService {

  /**
    *
    * check username and password
    * if exist then return the token
    *
    * @param name
    * @param password
    * @return
    */
  def login(name: String, password: String) : Future[String]

  /**
    * extract current token given to user id
    *
    * @param token
    * @return
    */
  def extractToken(token: String) : String

  def showAll : Future[Seq[User]]

}
