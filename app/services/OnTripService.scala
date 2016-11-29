package services

/**
  * Created by mirzaakhena on 11/28/16.
  */
trait OnTripService {

  /**
    * get Driver from id
    * get Trip object by id_driver from Driver object
    * make sure Trip state is "APPROACHED"
    * update state Trip object to "ONTRIP"
    * get Passenger object from id_passenger in Trip object
    * update state Passenger object to "GORIDE"
    *
    * @param idDriver
    */
  def driverStartTrip(idDriver: Int)

  /**
    * get Driver from id
    * get Trip object by id_driver from Driver object
    * make sure Trip state is "ONTRIP"
    * update state Trip object to "FINISHED"
    * get Passenger object from id_passenger in Trip object
    * update state Passenger object to "AVAILABLE"
    * get Driver object from id_driver in Trip object
    * update state Driver object to "AVAILABLE"
    *
    * @param idDriver
    */
  def driverEndTrip(idDriver: Int)

}