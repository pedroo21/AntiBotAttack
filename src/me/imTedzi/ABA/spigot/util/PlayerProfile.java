package me.imTedzi.ABA.spigot.util;

import java.util.UUID;

public class PlayerProfile {

    private String playerName;

    private long userId;

    private UUID uuid;
    private boolean premium;
    private String lastIp;
    private long lastLogin;

    public PlayerProfile(long userId, UUID uuid, String playerName, boolean premium, String lastIp, long lastLogin) {
        this.userId = userId;
        this.uuid = uuid;
        this.playerName = playerName;
        this.premium = premium;
        this.lastIp = lastIp;
        this.lastLogin = lastLogin;
    }

    public PlayerProfile(UUID uuid, String playerName, boolean premium, String lastIp) {
        this(-1, uuid, playerName, premium, lastIp, System.currentTimeMillis());
    }

    public synchronized String getPlayerName() {
        return playerName;
    }

    public synchronized void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public synchronized long getUserId() {
        return userId;
    }

    public synchronized void setUserId(long generatedId) {
        this.userId = generatedId;
    }

    public synchronized UUID getUuid() {
        return uuid;
    }

    public synchronized void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public synchronized boolean isPremium() {
        return premium;
    }

    public synchronized void setPremium(boolean premium) {
        this.premium = premium;
    }

    public synchronized String getLastIp() {
        return lastIp;
    }

    public synchronized void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public synchronized long getLastLogin() {
        return lastLogin;
    }

    public synchronized void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }
}
