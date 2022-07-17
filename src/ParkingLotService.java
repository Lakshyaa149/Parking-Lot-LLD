import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParkingLotService {

    public int getFreeSlotsForVehicleTypeOnFloor(ParkingLot parkingLot,SlotType slotType,int floor){

        ParkingSlots parkingSlots=parkingLot.getParkingSlots().get(floor);

            if(slotType.equals(SlotType.BIKE)){
                List<Slot> freeBikeSlots=parkingSlots.getBikeSlots().parallelStream().filter(slot -> slot.isFree).collect(Collectors.toList());
                return freeBikeSlots.size();
            }
            else if(slotType.equals(SlotType.CYCLE)){
                List<Slot> freeCycleSlots=parkingSlots.getCycleClots().parallelStream().filter(slot -> slot.isFree).collect(Collectors.toList());
                return freeCycleSlots.size();
            }
            else{
                List<Slot> freeCarSlots=parkingSlots.getCarSlots().parallelStream().filter(slot -> slot.isFree).collect(Collectors.toList());
                return freeCarSlots.size();
            }
    }

    public Slot getFirstFreeSlotForVehicleType(ParkingLot parkingLot,SlotType slotType){
        for(int i=0;i<parkingLot.noOfFloors;i++){

            ParkingSlots parkingSlots=parkingLot.getParkingSlots().get(i);
            if(slotType.equals(SlotType.BIKE)){
                Optional<Slot> optionalSlot= parkingSlots.getBikeSlots().parallelStream().filter(slot -> slot.isFree).findFirst();
                if(optionalSlot.isPresent()){
                    return optionalSlot.get();
                }
            }
            else if(slotType.equals(SlotType.CYCLE)){
                Optional<Slot> optionalSlot= parkingSlots.getCycleClots().parallelStream().filter(slot -> slot.isFree).findFirst();
                if(optionalSlot.isPresent()){
                    return optionalSlot.get();
                }
            }
            else{
                Optional<Slot> optionalSlot= parkingSlots.getCarSlots().parallelStream().filter(slot -> slot.isFree).findFirst();
                if(optionalSlot.isPresent()){
                    return optionalSlot.get();
                }
            }
        }
     return null;
    }


    public ParkingTicket parkVehicle(String[] arr, ParkingLot parkingLot) {

        //Get Vehicle Type
        ParkingTicket parkingTicket=new ParkingTicket();
        parkingTicket.ticketId= UUID.randomUUID().toString();
        SlotType type=null;
        if(SlotType.BIKE.toString().equals(arr[0])){
             type=SlotType.BIKE;
        }
        else if(SlotType.CYCLE.toString().equals(arr[0])){
            type=SlotType.CYCLE;
        }
        else{
            type=SlotType.CAR;
        }

        for(int i=0;i<parkingLot.noOfFloors;i++){
            //getFirstFree Available slot starting from lowest floor.

            Slot slot=getFirstFreeSlotForVehicleType(parkingLot,type);
            Vehicle v=new Vehicle();
            v.setSlotType(type);
            v.setColor(arr[2]);
            v.setRegNo(arr[1]);
            if(Objects.nonNull(slot)){
                slot.setFree(Boolean.FALSE);
                parkingTicket.slot=slot;
                parkingTicket.vehicle=v;
                return parkingTicket;
            }
        }
    return null;
    }

    public void display(String input,ParkingLot parkingLot) {
        SlotType type=null;
        int totalSlots=0;
        if(SlotType.BIKE.toString().equals(input)){
            type=SlotType.BIKE;
            totalSlots=parkingLot.getParkingSlots().get(0).bikeSlots.size();
        }
        else if(SlotType.CYCLE.toString().equals(input)){
            type=SlotType.CYCLE;
            totalSlots=parkingLot.getParkingSlots().get(0).cycleClots.size();
        }
        else{
            type=SlotType.CAR;
            totalSlots=parkingLot.getParkingSlots().get(0).carSlots.size();
        }
        for(int i=0;i<parkingLot.noOfFloors;i++){
            int freeSlots=getFreeSlotsForVehicleTypeOnFloor(parkingLot,type,i);
            System.out.println("There are +"+freeSlots+" free slots available for "+type.toString());
            System.out.println("Occupied slots are="+(totalSlots-freeSlots));

        }
    }
}
