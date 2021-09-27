package hw;

import java.util.Queue;
import java.util.function.Consumer;

/** Observer for decoupling sliding window logic from routing updates. */
interface OutputObserver extends Consumer<Queue<String>> {}
