package me.realized.tokenmanager.config;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalLong;
import me.realized.tokenmanager.PcTokenManagerPlugin;
import me.realized.tokenmanager.util.Reloadable;
import me.realized.tokenmanager.util.config.AbstractConfiguration;
import org.bukkit.Material;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class WorthConfig extends AbstractConfiguration<PcTokenManagerPlugin> implements Reloadable {

    private final Map<Material, WorthData> worth = new HashMap<>();

    public WorthConfig(final PcTokenManagerPlugin plugin) {
        super(plugin, "worth");
    }

    @Override
    protected void loadValues(final FileConfiguration configuration) {
        configuration.getKeys(false).forEach(key -> {
            final Material type = Material.getMaterial(key);

            if (type == null) {
                return;
            }

            final Object value = configuration.get(key);

            if (value instanceof Number) {
                worth.put(type, new WorthData((long) ((int) value)));
                return;
            } else if (!(value instanceof MemorySection)) {
                return;
            }

            final MemorySection section = (MemorySection) value;
            final WorthData data = new WorthData();
            section.getKeys(false).forEach(durability -> {
                if (durability.equals("*")) {
                    data.baseWorthSet = true;
                    data.baseWorth = section.getLong(durability);
                    return;
                }

                data.extraWorth.put(Short.valueOf(durability), section.getLong(durability));
            });
            worth.put(type, data);
        });
    }

    @Override
    public void handleUnload() {
        worth.clear();
    }

    public OptionalLong getWorth(final Material material) {
        final WorthData data;
        return ((data = worth.get(material)) != null && data.baseWorthSet) ? OptionalLong.of(data.baseWorth) : OptionalLong.empty();
    }

    public OptionalLong getWorth(final ItemStack item) {
        final WorthData data;
        return (data = worth.get(item.getType())) != null ? data.worthOf(item) : OptionalLong.empty();
    }

    private static class WorthData {

        private boolean baseWorthSet;
        private long baseWorth;
        private Map<Short, Long> extraWorth;

        WorthData(final long baseWorth) {
            this.baseWorth = baseWorth;
            this.baseWorthSet = true;
        }

        WorthData() {
            extraWorth = new HashMap<>();
        }

        OptionalLong worthOf(final ItemStack item) {
            if (extraWorth != null) {
                final Long value = extraWorth.get(item.getDurability());

                if (value != null) {
                    return OptionalLong.of(value * item.getAmount());
                }
            }

            return baseWorthSet ? OptionalLong.of(baseWorth * item.getAmount()) : OptionalLong.empty();
        }
    }
}
