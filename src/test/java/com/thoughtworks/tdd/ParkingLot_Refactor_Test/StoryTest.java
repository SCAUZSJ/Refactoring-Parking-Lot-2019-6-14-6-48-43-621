package com.thoughtworks.tdd.ParkingLot_Refactor_Test;

import com.thoughtworks.tdd.ParkingLot_Refactor.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoryTest {
    /**
     * 停车时，会得到停车票
     *
     */
    @Test
    public void should_return_ticket_when_park_a_car() {
        //given
        Car car = new Car(1);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket ticket = parkingBoy.parking(car);
        //then
        Assertions.assertNotNull(ticket);
    }

    /**
     * 使用停车票，会得到一辆车
     */
    @Test
    public void should_return_car_when_submit_a_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket ticket = parkingBoy.parking(new Car(1));
        Car car = parkingBoy.redeemCar(ticket);

        //then
        Assertions.assertNotNull(car);
    }

    /**
     * 停车场可以停下多辆车
     */
    @Test
    public void should_return_carCount_when_park_cars() {
        //given
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        parkingBoy.parking(car1);
        parkingBoy.parking(car2);
        List<Car> carList = parkingLot.getCarList();
        //then
        Assertions.assertEquals(2,carList.size());
    }

    /**
     * 使用停车票可以得到相对应的车
     */
    @Test
    public void should_return_correct_car_when_submit_a_ticket() {
        //given
        Car audi = new Car(100);
        Car maserati = new Car(200);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket audiTicket=parkingBoy.parking(audi);
        Ticket maseratiTicket = parkingBoy.parking(maserati);
        Car car = parkingBoy.redeemCar(maseratiTicket);

        //then
        Assertions.assertEquals(maserati,car);
    }

    /**
     *  没有给票，则得不到车
     */
    @Test
    public void should_return_null_when_do_not_submit_ticket() {
        //given
        Car maserati = new Car(200);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket maseratiTicket = parkingBoy.parking(maserati);
        Car car = parkingBoy.redeemCar(null);

        //then
        Assertions.assertNull(car);
    }

    /**
     *  给错了票，则得不到车
     */
    @Test
    public void should_return_null_when_submit_error_ticket() {
        //given
        Car maserati = new Car(200);
        Ticket ticket = new Ticket(23);
        ticket.setParkingLotId(1);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket maseratiTicket = parkingBoy.parking(maserati);
        Car car = parkingBoy.redeemCar(ticket);

        //then
        Assertions.assertNull(car);
    }

    /**
     *  票已使用，则得不到车
     */
    @Test
    public void should_return_null_when_ticket_is_invalidity() {
        //given
        Car maserati = new Car(200);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket maseratiTicket = parkingBoy.parking(maserati);
        Car car = parkingBoy.redeemCar(maseratiTicket);
        Car car2 = parkingBoy.redeemCar(maseratiTicket);

        //then
        Assertions.assertNull(car2);
    }

    /**
     *  停车场停满车（10辆）,则无法再停车
     */
    @Test
    public void should_return_null_when_parking_lot_is_full_of_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);
        for(int i=1;i<=10;i++){
            parkingBoy.parking(new Car(i));
        }
        Car car11 = new Car(11);

        //when
        Ticket ticket = parkingBoy.parking(car11);
        //then
        Assertions.assertNull(ticket);
    }

    /**
     *  给已使用票,能询问到错误原因
     */
    @Test
    public void should_get_error_msg_when_ticket_is_invalidity() {
        //given
//        Car maserati = new Car(200);
        Ticket ticket = new Ticket(1);
        ticket.setValidity(false);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
//        Ticket maseratiTicket = parkingBoy.parking(maserati);
        parkingBoy.redeemCar(ticket);

        //then
        Assertions.assertEquals("Unrecognized parking ticket.",parkingBoy.getErrorMsg());
    }

    /**
     *  给泊车人没提供的票,能询问到错误原因
     */
    @Test
    public void should_get_error_msg_when_ticket_is_not_provide() {
        //given
        Car maserati = new Car(200);
        Ticket ticket = new Ticket(1);
        ticket.setParkingLotId(1);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        Ticket maseratiTicket = parkingBoy.parking(maserati);
        parkingBoy.redeemCar(ticket);

        //then
        Assertions.assertEquals("Unrecognized parking ticket.",parkingBoy.getErrorMsg());
    }

    /**
     *  没给票,能询问到错误原因
     */
    @Test
    public void should_get_error_msg_when_do_not_submit_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);

        //when
        parkingBoy.redeemCar(null);

        //then
        Assertions.assertEquals("Please provide your parking ticket.",parkingBoy.getErrorMsg());
    }

    /**
     *  停车场停满车（10辆）,能询问到错误原因
     */
    @Test
    public void should_get_error_msg_when_parking_lot_is_full_of_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot);
        for(int i=1;i<=10;i++){
            parkingBoy.parking(new Car(i));
        }
        Car car11 = new Car(11);

        //when
        parkingBoy.parking(car11);
        //then
        Assertions.assertEquals("Not enough position.",parkingBoy.getErrorMsg());
    }
    /**
     *  停车场1停满车（10辆）,停车场2未停满，则停到停车场2
     */
    @Test
    public void should_park_the_next_parking_lot_when_parking_lot_one_is_full_of_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1),new ParkingLot(2)));
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLots);
        for(int i=1;i<=10;i++){
            parkingBoy.parking(new Car(i));
        }
        Car car11 = new Car(11);

        //when
        Ticket ticket = parkingBoy.parking(car11);
        //then
        Assertions.assertEquals(new Integer(2),ticket.getParkingLotId());
    }

    /**
     *  smart parkingboy 可以把车停到剩余车位最多的停车场
     */
    @Test
    public void should_choose_the_best_parking_lot_when_park_the_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(new ParkingLot(1,2),new ParkingLot(2,5),new ParkingLot(3,10)));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(1,parkingLots);
        //when
        Ticket ticket = smartParkingBoy.parking(new Car(1));
        //then
        Assertions.assertEquals(new Integer(3),ticket.getParkingLotId());
    }

    /**
     *  smart parkingboy 可以把车停到剩余车位最多的停车场
     */
    @Test
    public void should_choose_usage_min_parking_lot_when_park_the_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1,5);
        ParkingLot parkingLot2 = new ParkingLot(2,3);
        List<ParkingLot> parkingLots = new ArrayList<>(Arrays.asList(parkingLot1,parkingLot2));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(1,parkingLots); //super 管理2个停车场
        ParkingBoy parkingBoy = new ParkingBoy(1,parkingLot1); //普通男孩管理1

        //when
        Ticket ticket1 = parkingBoy.parking(new Car(1));

        Ticket ticket2 = superSmartParkingBoy.parking(new Car(2));
        //then
        /**
         * super smart parking boy停车时，1号停车场剩余车位4,可用率80%，2号停车位剩余车位3，可用率100%
         * 因此停在2号停车场
         */
        Assertions.assertEquals(new Integer(1),ticket1.getParkingLotId());
        Assertions.assertEquals(new Integer(2),ticket2.getParkingLotId());
    }

    /**
     *  service manager 可以自己在自己的停车场 停车
     */
    @Test
    public void should_return_ticket_when_manager_store_car_in_his_own_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot(1,5);
        ServiceManager serviceManager = new ServiceManager(1,parkingLot,null);//没手下
        Car car = new Car(1);

        //when
        Ticket ticket = serviceManager.parking(car);
        //then

        Assertions.assertEquals(new Integer(1),ticket.getParkingLotId());
    }

    /**
     *  service manager 可以自己在自己的停车场 取车
     */
    @Test
    public void should_return_car_when_manager_fetch_car_in_his_own_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot(1,5);
        ServiceManager serviceManager = new ServiceManager(1,parkingLot,null);//没手下
        Car car = new Car(1);
        //when
        Ticket ticket = serviceManager.parking(car);
        Car newCar = serviceManager.redeemCar(ticket);
        //then

        Assertions.assertEquals(car,newCar);
    }

    /**
     *  service manager 可以指定某个泊车男孩去停车
     */
    @Test
    public void should_return_ticket_when_manager_specify_a_parking_boy_to_store() {
        //given
        ParkingLot parkingLot = new ParkingLot(1,5);
        ParkingBoy parkingBoy =new ParkingBoy(1,parkingLot);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ServiceManager serviceManager = new ServiceManager(1,(ParkingLot) null,parkingBoys);//管理着parkingBoy

        //when
        Ticket ticket = serviceManager.parking(1,new Car(1));//让id为1的boy去停车
        //then

        Assertions.assertEquals(new Integer(1),ticket.getOperatorId()); //停车人的id与预期相符
    }

    /**
     *  service manager 可以指定某个泊车男孩去停车
     */
    @Test
    public void should_return_car_when_manager_specify_a_parking_boy_to_fetch() {
        //given
        ParkingLot parkingLot = new ParkingLot(1,5);
        ParkingBoy parkingBoy =new ParkingBoy(1,parkingLot);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ServiceManager serviceManager = new ServiceManager(1,parkingLot,parkingBoys);//管理着parkingBoy
        Car car = new Car(1);
        //when
        Ticket ticket = serviceManager.parking(car);
        Car newCar = (Car) serviceManager.redeemCar(1,ticket);//让id为1的boy去取车
        //then

        Assertions.assertEquals(car,newCar); //取到之前停的车
    }

    /**
     *  service manager 可以指定某个泊车男孩去停车,车位满，manager向顾客显示错误信息
     */
    @Test
    public void should_return_error_msg_when_manager_specify_a_parking_boy_to_store_but_fail() {
        //given
        ParkingLot parkingLot = new ParkingLot(1,0);
        ParkingBoy parkingBoy =new ParkingBoy(1,parkingLot);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ServiceManager serviceManager = new ServiceManager(1,(ParkingLot) null,parkingBoys);//管理着parkingBoy

        //when
        Ticket ticket = serviceManager.parking(1,new Car(1));//让id为1的boy去停车
        //then

        Assertions.assertEquals("Not enough position.",serviceManager.getErrorMsg());
    }

    /**
     *  service manager 指定某个泊车男孩拿 错误 的票去取车
     */
    @Test
    public void should_return_error_msg_when_manager_specify_a_parking_boy_to_fetch_but_fail() {
        //given
        ParkingLot parkingLot = new ParkingLot(1,5);
        ParkingBoy parkingBoy =new ParkingBoy(1,parkingLot);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        ServiceManager serviceManager = new ServiceManager(1,parkingLot,parkingBoys);//管理着parkingBoy
        Car car = new Car(1);
        //when
        serviceManager.parking(car);
        Ticket ticket = new Ticket(2);
        ticket.setParkingLotId(1);
        Car newCar = (Car) serviceManager.redeemCar(1,ticket);//让1号boy去取2车，错误的票
        //then

        Assertions.assertEquals("Unrecognized parking ticket.",serviceManager.getErrorMsg()); //取到之前停的车
    }



}
