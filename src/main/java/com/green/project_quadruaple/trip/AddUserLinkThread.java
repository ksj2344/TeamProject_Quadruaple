package com.green.project_quadruaple.trip;

public class AddUserLinkThread implements Runnable {

    private final String uuid;

    public AddUserLinkThread(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(86_400_000);
            TripService.addUserLinkMap.remove(uuid);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
