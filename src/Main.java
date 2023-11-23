import java.util.*;

public class Main {

    public static void main(String[] args) {

        Bus bus1 = new Bus(1);
        Bus bus2 = new Bus(2);
        Bus bus3 = new Bus(3);

        BusStopp stopA = new BusStopp("StopA");
        BusStopp stopB = new BusStopp("StopB");
        BusStopp stopC = new BusStopp("StopC");

        Route route = new Route();


        route.addStop(bus1, Arrays.asList(stopA, stopB, stopC));
        route.addStop(bus2, Arrays.asList(stopB, stopC, stopA));
        route.addStop(bus3, Arrays.asList(stopC, stopA, stopB));


        route.setSchedule(stopA, bus1, Arrays.asList("7:00", "7:15", "7:30"));
        route.setSchedule(stopB, bus1, Arrays.asList("7:10", "7:25", "7:40"));
        route.setSchedule(stopC, bus1, Arrays.asList("7:20", "7:35", "7:50"));

        route.setSchedule(stopB, bus2, Arrays.asList("7:05", "7:20", "7:35"));
        route.setSchedule(stopC, bus2, Arrays.asList("7:15", "7:30", "7:45"));
        route.setSchedule(stopA, bus2, Arrays.asList("7:25", "7:40", "7:55"));

        route.setSchedule(stopC, bus3, Arrays.asList("7:30", "7:45", "8:00"));
        route.setSchedule(stopA, bus3, Arrays.asList("7:40", "7:55", "8:10"));
        route.setSchedule(stopB, bus3, Arrays.asList("7:50", "8:05", "8:20"));


        Random random = new Random();
        for (int i = 4; i <= 10; i++) {
            Bus bus = new Bus(i);
            List<BusStopp> stops = Arrays.asList(stopA, stopB, stopC);
            Collections.shuffle(stops);
            route.addStop(bus, stops);

            List<String> scheduleTimes = new ArrayList<>();
            for (int j = 7; j <= 10; j++) {
                int minutes = random.nextInt(31);
                scheduleTimes.add(String.format("%d:%02d", j, minutes));
            }
            route.setSchedule(stops.get(0), bus, scheduleTimes);
        }


        System.out.println("Номера всех автобусов в базе: " + route.getAllBusNumbers());
        System.out.println("Адреса всех остановок: " + route.getAllBusStopAddresses());

        int selectedBusNumber = 2;
        System.out.println("Маршрут следования для автобуса #" + selectedBusNumber + ": " + route.getRoute(selectedBusNumber));
        System.out.println("Расписание прибытия на остановки для автобуса #" + selectedBusNumber + ": " + route.getScheduleForBus(selectedBusNumber));

        String selectedBusStop = "StopB";
        route.printScheduleForBusStop(selectedBusStop);
    }
}
