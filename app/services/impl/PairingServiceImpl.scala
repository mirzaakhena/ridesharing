package services.impl

import services.PairingService

/**
  * Created by mirzaakhena on 11/28/16.
  */
class PairingServiceImpl extends PairingService {


  override def passengerRequestTripToDrivers(idPassenger: Int): Unit = ???

  override def driverGetPassengerRequests(idDriver: Int): Unit = ???

  override def driverAcceptPassengerRequest(idTrip: Int): Unit = ???

  override def passengerCheckRequest(idPassenger: Int): Unit = ???
}
