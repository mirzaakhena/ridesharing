package services

/**
  * Created by mirzaakhena on 11/28/16.
  */
trait PairingService {

  /**
    * get Passenger from id
    * get an state in Passenger then make sure the role is "AVAILABLE"
    * make sure NO Trip object with id_user and "REQUESTED" state
    * create object Trip
    * set id_passenger
    * set state to "REQUESTED"
    * set lat lon start and end
    *
    * @param idPassenger
    */
  def passengerRequestTripToDrivers(idPassenger: Int)

  /**
    * get Driver from id
    * get an state in Driver then make sure the role is "AVAILABLE"
    * get all of Trip object where the state is "REQUESTED"
    *
    * @param idDriver
    */
  def driverGetPassengerRequests(idDriver: Int)

  /**
    * get Driver from id
    * get an state in Driver then make sure the role is "AVAILABLE"
    * get Trip by id_trip then make sure state is "REQUESTED"
    * set id_driver in Trip object
    * update state Trip object to "APPROACHED"
    * update state Driver to "BUSY"
    * update state Passenger to "WAITING"
    *
    * @param idTrip
    */
  def driverAcceptPassengerRequest(idTrip: Int)

  /**
    * get Passenger from id
    * get an state in Passenger whereas "AVAILABLE" | "WAITING" | "GORIDE"
    * return the state
    *
    * @param idPassenger
    */
  def passengerCheckRequest(idPassenger: Int)

}
