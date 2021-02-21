package me.realized.tokenmanager.util.hook;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginHook<P extends JavaPlugin> {

    protected final P plugin;

    private final String name;

    public PluginHook(final P plugin, final String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
