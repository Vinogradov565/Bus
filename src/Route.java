import java.util.*;

public class Route {
    private Map<Integer, List<BusStopp>> routes;
    private Map<BusStopp, Map<Integer, List<String>>> schedule;

    public Route() {
        this.routes = new HashMap<>();
        this.schedule = new HashMap<>();
    }

    public void addStop(Bus bus, List<BusStopp> stops) {
        routes.put(bus.getNumberOfBus(), stops);
    }

    public List<BusStopp> getRoute(int numberOfBus) {
        return routes.get(numberOfBus);
    }

    public Set<Integer> getAllBusNumbers() {
        return routes.keySet();
    }

    public Set<String> getAllBusStopAddresses() {
        Set<String> stopAddresses = new HashSet<>();
        for (List<BusStopp> stops : routes.values()) {
            for (BusStopp stop : stops) {
                stopAddresses.add(stop.getAddress());
            }
        }
        return stopAddresses;
    }

    public void setSchedule(BusStopp stop, Bus bus, List<String> arrivalTimes) {
        schedule.computeIfAbsent(stop, k -> new HashMap<>()).put(bus.getNumberOfBus(), arrivalTimes);
    }

    public Map<Integer, List<String>> getScheduleForBus(int numberOfBus) {
        Map<Integer, List<String>> busSchedule = new HashMap<>();
        for (Map.Entry<BusStopp, Map<Integer, List<String>>> entry : schedule.entrySet()) {
            if (entry.getValue().containsKey(numberOfBus)) {
                busSchedule.put(numberOfBus, entry.getValue().get(numberOfBus));
            }
        }
        return busSchedule;
    }

    public void printScheduleForBusStop(String stopAddress) {
        for (Map.Entry<BusStopp, Map<Integer, List<String>>> entry : schedule.entrySet()) {
            if (entry.getKey().getAddress().equals(stopAddress)) {
                System.out.println("Schedule for Bus Stop " + stopAddress + ":");
                for (Map.Entry<Integer, List<String>> busEntry : entry.getValue().entrySet()) {
                    System.out.println("Bus #" + busEntry.getKey() + ": " + busEntry.getValue());
                }
                return;
            }
        }
        System.out.println("Bus Stop " + stopAddress + " not found.");
    }
}
