package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class CallServiceByIdObserver extends BusObserver<CallServiceByIdObserver.CallServiceItemPressed> {
    public CallServiceByIdObserver() {
        super(CallServiceItemPressed.class);
    }

    public static class CallServiceItemPressed {
        public String id;

        public CallServiceItemPressed(String id){
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}