import java.util.Map;

public class ParkingLot {


    String parkingLotId;
    int  noOfFloors;
    Map<Integer,ParkingSlots> parkingSlots;
    boolean seats[][];
    int noOfSlots;
    static Map<String,ParkingTicket> parkingTicketMap;

    public boolean[][] getSeats() {
        return seats;
    }

    public void setSeats(boolean[][] seats) {
        this.seats = seats;
    }

    public static Map<String, ParkingTicket> getParkingTicketMap() {
        return parkingTicketMap;
    }

    public static void setParkingTicketMap(Map<String, ParkingTicket> parkingTicketMap) {
        ParkingLot.parkingTicketMap = parkingTicketMap;
    }



    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public Map<Integer, ParkingSlots> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(Map<Integer, ParkingSlots> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public boolean[][] getSeat() {
        return seats;
    }

    public void setSeat(boolean[][] seat) {
        this.seats = seat;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
    public int getNoOfSlots() {
        return noOfSlots;
    }

    public void setNoOfSlots(int noOfSlots) {
        this.noOfSlots = noOfSlots;
    }




}
