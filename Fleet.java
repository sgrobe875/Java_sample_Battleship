/**
   The Fleet class initializes the five ship types and manages changes to them, such
   as hitting and sinking.
*/
public class Fleet
{  
   //variables
   private Ship battleShip;
   private Ship aircraftCarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   //constructor
   /**
      The constructor creates an instance of each of the five ships in the fleet.
   */
   public Fleet()
   {
      battleShip = new Battleship();
      aircraftCarrier = new AircraftCarrier();
      cruiser = new Cruiser();
      sub = new Sub();
      destroyer = new Destroyer();
   }
   
   //methods
   /**
      The updateFleet method takes in a specific ship type, marks that ship as being hit,
      and then returns whether or not that hit resulted in the ship sinking.
      @param s the ship type that is to be updated
      @return whether or not the ship has sunk
   */
   public boolean updateFleet(ShipType s)
   {  
      // determine which ship needs to be updated
      Ship ship;
      if (s.equals(ShipType.ST_AIRCRAFT_CARRIER))
         ship = aircraftCarrier;
      else if (s.equals(ShipType.ST_BATTLESHIP))
         ship = battleShip;
      else if (s.equals(ShipType.ST_CRUISER))
         ship = cruiser;
      else if (s. equals(ShipType.ST_DESTROYER))
         ship = destroyer;
      else
         ship = sub;
      
      // have the ship register a hit
      ship.hit();
   
      // return whether or not the ship has sunk
      if (ship.getSunk())
         return true;

      return false;
   }  
   
   /**
      The gameOver method determines when the game as ended by checking each ship's status.
      Returns true when all ships in the fleet have been sunk, false otherwise.
      @return whether or not the game has ended (all ships are sunk)
   */
   public boolean gameOver()
   {
      if (battleShip.getSunk() && aircraftCarrier.getSunk() && cruiser.getSunk() &&
            sub.getSunk() && destroyer.getSunk() )
         return true;

      return false;
   }
}