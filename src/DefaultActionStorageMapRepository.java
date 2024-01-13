import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultActionStorageMapRepository implements ActionStorageRepository {

    private final Map<Integer, Integer> storage = new HashMap<>();

    @Override
    public void store(int timestamp) {
        Integer existingValue = storage.getOrDefault(timestamp, 0);
        storage.put(timestamp, existingValue + 1);
    }

    @Override
    public int countInRange(int lowerLimit, int upperLimit) {
        return (int) storage.keySet()
            .stream()
            .filter(it -> it > lowerLimit && it <= upperLimit)
            .map(it -> storage.getOrDefault(it, 0))
            .collect(Collectors.summarizingInt(Integer::intValue))
            .getSum();
    }
}
