package me.realized.tokenmanager.hook.hooks;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.realized.tokenmanager.PcTokenManagerPlugin;
import me.realized.tokenmanager.util.hook.PluginHook;

public class MVdWPlaceholderHook extends PluginHook<PcTokenManagerPlugin> {

    public MVdWPlaceholderHook(final PcTokenManagerPlugin plugin) {
        super(plugin, "MVdWPlaceholderAPI");

        final Placeholders placeholders = new Placeholders();
        PlaceholderAPI.registerPlaceholder(plugin, "pct_tokens", placeholders);
        PlaceholderAPI.registerPlaceholder(plugin, "pct_tokens_raw", placeholders);
        PlaceholderAPI.registerPlaceholder(plugin, "pct_tokens_commas", placeholders);
        PlaceholderAPI.registerPlaceholder(plugin, "pct_tokens_formatted", placeholders);
        PlaceholderAPI.registerPlaceholder(plugin, "pct_rank", placeholders);

        for (int i = 1; i <= 10; i++) {
            PlaceholderAPI.registerPlaceholder(plugin, "pct_top_name_" + i, placeholders);
            PlaceholderAPI.registerPlaceholder(plugin, "pct_top_tokens_" + i, placeholders);
        }
    }

    public class Placeholders implements PlaceholderReplacer {

        @Override
        public String onPlaceholderReplace(final PlaceholderReplaceEvent event) {
            return plugin.handlePlaceholderRequest(event.getPlayer(), event.getPlaceholder().substring(3));
        }
    }
}
