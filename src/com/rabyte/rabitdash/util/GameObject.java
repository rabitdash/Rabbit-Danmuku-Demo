package com.rabyte.rabitdash.util;


public class GameObject {
    public boolean active = false;
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
