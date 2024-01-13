public interface ActionStorageRepository {

    void store(int timestamp);

    int countInRange(int lowerLimit, int upperLimit);
}
