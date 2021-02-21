package me.realized.tokenmanager.command;

import me.realized.tokenmanager.PcTokenManagerPlugin;
import me.realized.tokenmanager.config.Config;
import me.realized.tokenmanager.data.DataManager;
import me.realized.tokenmanager.shop.ShopConfig;
import me.realized.tokenmanager.shop.ShopManager;
import me.realized.tokenmanager.util.command.AbstractCommand;
import me.realized.tokenmanager.util.profile.ProfileUtil;
import org.bukkit.command.CommandSender;

public abstract class BaseCommand extends AbstractCommand<PcTokenManagerPlugin> {

    protected final Config config;
    protected final ShopConfig shopConfig;
    protected final ShopManager shopManager;
    protected final DataManager dataManager;
    protected final boolean online;

    public BaseCommand(final PcTokenManagerPlugin plugin, final String name, final String permission, final boolean playerOnly) {
        this(plugin, name, null, permission, 0, playerOnly);
    }

    public BaseCommand(final PcTokenManagerPlugin plugin, final String name, final String usage, final String permission, final int length,
                       final boolean playerOnly, final String... aliases) {
        super(plugin, name, usage, permission, length, playerOnly, aliases);
        this.config = plugin.getConfiguration();
        this.shopConfig = plugin.getShopConfig();
        this.shopManager = plugin.getShopManager();
        this.dataManager = plugin.getDataManager();

        final String mode = config.getOnlineMode();
        this.online = mode.equals("auto") ? ProfileUtil.isOnlineMode() : mode.equals("true");
    }

    protected void sendMessage(final CommandSender receiver, final boolean config, final String in, final Object... replacers) {
        plugin.getLang().sendMessage(receiver, config, in, replacers);
    }

    @Override
    protected void handleMessage(final CommandSender sender, final MessageType type, final String... args) {
        switch (type) {
            case PLAYER_ONLY:
                sendMessage(sender, false, "&cThis command can only be executed by a player!");
                break;
            case NO_PERMISSION:
                sendMessage(sender, true, "ERROR.no-permission", "permission", args[0]);
                break;
            case SUB_COMMAND_INVALID:
                sendMessage(sender, true, "ERROR.invalid-sub-command", "command", args[0], "input", args[1]);
                break;
            case SUB_COMMAND_USAGE:
                sendMessage(sender, true, "COMMAND.sub-command-usage", "command", args[0], "usage", args[1]);
                break;
        }
    }
}
