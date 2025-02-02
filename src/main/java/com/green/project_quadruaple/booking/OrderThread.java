package com.green.project_quadruaple.booking;

public class OrderThread implements Runnable {

    private final long userId;

    public OrderThread(long userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1_800_000 );
            BookingService.kakaoTidSession.remove(userId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
