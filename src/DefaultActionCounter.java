public class DefaultActionCounter implements ActionCounter {

    private static final int TIME_DURATION = 300;
    private final ActionStorageRepository actionStorageRepository;

    public DefaultActionCounter(ActionStorageRepository actionStorageRepository) {
        this.actionStorageRepository = actionStorageRepository;
    }

    @Override
    public void call(int timestamp) {
        actionStorageRepository.store(timestamp);
    }

    @Override
    public int getActions(int timestamp) {
        int lowerTimeLimit = Math.max(timestamp - TIME_DURATION, 0);
        return actionStorageRepository.countInRange(lowerTimeLimit, timestamp);
    }
}
