package com.company;

public class Producer implements Runnable {

    private boolean isPrime(int x) {
        int cnt = 0;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                cnt++;
                break;
            }
        }
        return (cnt == 0);
    }

    int n;
    int cnt = 0;
    Buffer buffer;
    long startTime;
    int mxPrime = 1;

    public Producer(int n, Buffer buffer, long startTime) {
        this.n = n;
        this.buffer = buffer;
        this.startTime = startTime;
    }

    @Override
    public void run() {


        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                buffer.add(Integer.toString(i));
                cnt++;
                mxPrime = i;

            }

        }
        buffer.finish = true;


    }
}
