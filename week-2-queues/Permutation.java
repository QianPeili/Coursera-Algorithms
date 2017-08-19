import edu.princeton.cs.algs4.StdIn;

/**
 * Created by qianpeili on 2017/8/17.
 */
public class Permutation {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        String str;
        while (!StdIn.isEmpty()) {
            str = StdIn.readString();
            queue.enqueue(str);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
