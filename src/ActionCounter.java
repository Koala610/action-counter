public interface ActionCounter {

    void call(int timestamp);

    int getActions(int timestamp);
}
