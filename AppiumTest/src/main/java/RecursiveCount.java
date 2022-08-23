import static jdk.nashorn.internal.objects.Global.print;

public class RecursiveCount {
    public static void recursiveCount( int count ) {
        System.out.println( count );
        if ( count < 10 ) {
            recursiveCount(count + 1);
        }
    }

    public static void recursiveCount2(int count) {
        if (count < 10) {
            recursiveCount2(count + 1);
        }
        System.out.println( count );
    }

    public static int fib(int n) {
        //피보나치 수열
        if (n < 3) {
            return 1;
        }
        return fib(n-1) + fib (n-2);
    }

    public static void fib2(int n) {
        //피보나치 수열, 재귀 안쓰고
        int previous = 0;
        int current = 1;
        int temp;
        int i = 0;
        while (i < n) {
            System.out.println(current);
            temp = previous;
            previous = current;
            current = previous + temp;
            i++;
        }
    }


    /*
    recursiveCount2(1)
        recursiveCount2(2)
            recursiveCount2(3)
                        ...
                            recursiveCount2(9)
                                recursiveCount2(10)
                                  print(10)
                            print(9)
                        print(8)

     */

    public static void main(String[] args) {
//        recursiveCount(1);
//        recursiveCount2(1);
//        for (int i = 1; i < 11; i++) {
//            System.out.println((fib(i)));
//        }
        fib2(10);
    }
}



