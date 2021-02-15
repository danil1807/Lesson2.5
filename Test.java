
public class Test {

    static final int SIZE = 1000000;
    static final int HALF = SIZE / 2;

    public synchronized void createArr1() {
        float[] arr = new float[SIZE];


        fill1ToArray(arr);
        long a = System.currentTimeMillis();
        System.out.println(a);

        newDataToArray(arr);
        long b = System.currentTimeMillis();
        System.out.println(b);

        System.out.println(b - a);
    }

    public synchronized void createArr2() {
        float[] arr = new float[SIZE];
        float[] arr12 = new float[HALF];
        float[] arr22 = new float[HALF];

        fill1ToArray(arr);

        long a = System.currentTimeMillis();
        System.out.println(a);

        System.arraycopy(arr, 0, arr12, 0, HALF);
        System.arraycopy(arr, HALF, arr22, 0, HALF);

        Test test = new Test();
        new Thread(() -> test.newDataToArray(arr12)).start();
        new Thread(() -> test.newDataToArray(arr22)).start();

        System.arraycopy(arr12, 0, arr, 0, HALF);
        System.arraycopy(arr22, 0, arr, HALF, HALF);


        long b = System.currentTimeMillis();
        System.out.println(b);

        System.out.println(b - a);
    }

    void newDataToArray(float array[]) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    void fill1ToArray(float array[]) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
    }
}