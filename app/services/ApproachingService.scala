package services

/**
  * Created by mirzaakhena on 11/28/16.
  */
trait ApproachingService {

  /**
    * get Driver from id
    * set lat and lon in Driver object
    *
    * @param idDriver
    * @param lat
    * @param lng
    */
  def driverSendLocation(idDriver: Int, lat: Double, lng: Double)

  /**
    * get Passenger from id
    * make sure the state of Passenger is "WAITING"
    * get Trip object with id_user and state is "REQUESTED"
    * get Driver from id_driver in Trip Object
    * return driver info and location
    *
    * @param idPassenger
    */
  def getDriverLocation(idPassenger: Int)

}
