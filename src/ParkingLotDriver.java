import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ParkingLotDriver {
    // private static Object fillingFloorToSlots;
    private static   ParkingLotService parkingLotService=new ParkingLotService();

    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input ParkingLot Id, no of floors. No of slots in each floor");
        String input=reader.readLine();
        String arr[]=input.split(" ");
        ParkingLot parkingLot=ParkingLotFactory.getParkingLotInstance();
        parkingLot.setParkingLotId(arr[0]);
        parkingLot.setNoOfFloors(Integer.parseInt(arr[1]));
        parkingLot.setNoOfSlots(Integer.parseInt(arr[2]));
        System.out.println("Input Bike start 0  and  input ending.");
        System.out.println("Input Cycle slots start will be bike slot plus 1 input and ending.");
        System.out.println("else will be car slots");
        input=reader.readLine();
         arr=input.split(" ");
         //validating user input for slots assigned to diff vehicles.
        while (!validateSlotsInput(arr)){
            System.out.println("Input Bike start 0  and  input ending.");
            System.out.println("Input Cycle slots start will be bike slot plus 1 input and ending.");
            System.out.println("else will be car slots");
            input=reader.readLine();
            arr=input.split(" ");
        }
        //initialize floor to parking slots.
        parkingLot.parkingSlots=new HashMap<>();
        fillingFloorToSlots(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),parkingLot.getNoOfSlots(),parkingLot.getNoOfFloors(),parkingLot);
         //initalizing parkingTicketId to Parking Ticket.
        ParkingLot.parkingTicketMap=new HashMap<>();
        while(true) {
            System.out.println("For ParkingVehicle press 1");
            System.out.println("For unparking vehicle press2");
            System.out.println("For display  vehicle free slots ,occupied slots on each floor");
            input=reader.readLine();
            Integer c=Integer.parseInt(input);


            switch (c){
                case 1:{
                   System.out.println("input vehicleType, Reg no,color");
                    input=reader.readLine();
                    arr=input.split(" ");
                    ParkingTicket ticket=parkingLotService.parkVehicle(arr,parkingLot);
                    if(Objects.nonNull(ticket)){
                        System.out.println("Ticket info ");
                        System.out.println("Ticket with id"+ticket.ticketId+""+"----floor---"+ticket.slot.row+"---slot----"+ticket.slot.col);
                        ParkingLot.getParkingTicketMap().put(ticket.ticketId,ticket);
                    }
                    else{
                        System.out.println("Parking is FULL !!!!!!!!!!");
                    }
                    break;
                }
                case 2:{
                  System.out.println("unparking vehicle input id");
                    input=reader.readLine();


                    if(!ParkingLot.getParkingTicketMap().containsKey(input)){
                        System.out.println("There is no parking with parking id="+input);
                    }
                    else{
                        ParkingTicket parkingTicket=ParkingLot.getParkingTicketMap().get(input);
                        parkingTicket.slot.setFree(Boolean.TRUE);
                    }
                    break;
                }
                case 3:
                    System.out.println("Input vehicle Car,Bike,Cycle for which you need slots info");
                    input=reader.readLine();
                    parkingLotService.display(input,parkingLot);
                    break;
            }
        }


    }

    private static void fillingFloorToSlots(int bikeSlotsEnd,int cycleslotsend,int nooFSlots,int noOfFloors,ParkingLot parkingLot) {
        parkingLot.seats=new boolean[parkingLot.noOfFloors][nooFSlots];

        for(int i=0;i<parkingLot.noOfFloors;i++){
            ParkingSlots p=new ParkingSlots();
            p.bikeSlots=new ArrayList<>();
            p.cycleClots=new ArrayList<>();
            p.carSlots=new ArrayList<>();
            int j=0;
          for( j=0;j<bikeSlotsEnd;j++){
              Slot s=new Slot(i,j,SlotType.BIKE);
              s.setFree(Boolean.TRUE);
              p.getBikeSlots().add(s);
          }
          int k=j;
            for( k=j;k<cycleslotsend;k++){
                Slot s=new Slot(i,k,SlotType.CYCLE);
                s.setFree(Boolean.TRUE);
                 p.getCycleClots().add(s);
            }


            for(int z=k;z<nooFSlots;z++){
                Slot s=new Slot(i,z,SlotType.CAR);
                s.setFree(Boolean.TRUE);
                p.getCarSlots().add(s);
            }
            parkingLot.getParkingSlots().put(i,p);
        }
    }

    private static boolean validateSlotsInput(String[] arr) {

        Integer a1=Integer.parseInt(arr[0]);
        Integer a2=Integer.parseInt(arr[1]);

        return a2>a1+1;

    }
}
