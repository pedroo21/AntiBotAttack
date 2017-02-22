package me.imTedzi.ABA.spigot;

import me.imTedzi.ABA.spigot.commands.ABACommand;
import me.imTedzi.ABA.spigot.listeners.PingListener;
import me.imTedzi.ABA.spigot.listeners.PlayerJoinListener;
import me.imTedzi.ABA.spigot.listeners.PreLoginListener;
import me.imTedzi.ABA.spigot.managers.BotManager;
import me.imTedzi.ABA.spigot.managers.Config;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.UUID;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    private FileConfiguration configuration;
    private File configFile;

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        BotManager manager = new BotManager(this);
        manager.AntiBot();
        manager.refreshPing();
        new PingListener(this);
        new PreLoginListener(this);
        new PlayerJoinListener(this);
        Config.loadConfig();
        getCommand("aba").setExecutor(new ABACommand(this));
        if(Config.PROTECTION_ENABLED) {
            this.getLogger().log(Level.INFO, Config.color("Protection is " + ChatColor.GREEN + "enabled " +
                    "(" + getDescription().getVersion() + ")"));
        }
        else {
            this.getLogger().log(Level.INFO, Config.color("Protection is " + ChatColor.GREEN + "not enabled " +
                    "(" + getDescription().getVersion() + ")"));
        }
    }

    public void loadConfig() throws IOException {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        if(configFile == null)
            configFile = new File(getDataFolder(), "configs.yml");

        if(!configFile.exists()) {
            copy(getResource("configs.yml"), configFile);
        }

        configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            configuration.save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return configuration;
    }

    public static Main getInstance() {
        return instance;
    }
}
