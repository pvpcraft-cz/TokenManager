package me.realized.tokenmanager.api;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.UUID;
import me.realized.tokenmanager.shop.Shop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface TokenManager {


    /**
     * Get a shop by name.
     *
     * @param name Shop name
     * @return Optional containing the shop instance if found, otherwise empty
     */
    Optional<Shop> getShop(final String name);


    /**
     * Get a shop by inventory.
     *
     * @param inventory inventory to look up for matching shop instance
     * @return Optional containing the shop instance if found, otherwise empty
     */
    Optional<Shop> getShop(final Inventory inventory);


    /**
     * Get worth of the material defined in worth.yml.
     *
     * @param material Material to check for worth.
     * @return Optional containing the worth of the material if defined. Empty otherwise
     * @since v3.2.0
     */
    OptionalLong getWorth(final Material material);


    /**
     * Get worth of the item defined in worth.yml.
     *
     * @param item ItemStack to check for worth.
     * @return Optional containing the worth of the item if defined. Empty otherwise
     * @since v3.2.0
     */
    OptionalLong getWorth(final ItemStack item);


    /**
     * Get online player's token balance.
     *
     * @param player Player to get token balance
     * @return OptionalLong containing token balance if found, otherwise empty
     */
    OptionalLong getTokens(final Player player);


    /**
     * Set online player's token balance.
     *
     * @param player Player to set token balance
     * @param amount Amount to replace player's token balance
     */
    void setTokens(final Player player, final long amount);


    /**
     * Add an amount of tokens to online player's token balance.
     *
     * @param player Player to add tokens
     * @param amount Amount of tokens to add
     * @return true if add was successful, Otherwise false
     * @since v3.2.3
     */
    boolean addTokens(final Player player, final long amount);


    /**
     * Remove an amount of tokens from online player's token balance.
     *
     * @param player Player to remove tokens
     * @param amount Amount of tokens to remove
     * @return true if remove was successful, Otherwise false
     * @since v3.2.3
     */
    boolean removeTokens(final Player player, final long amount);


    /**
     * Set player's token balance.
     *
     * @param key {@link UUID#toString()} if server is in online mode, otherwise name of the player
     * @param amount Amount to replace player's token balance
     * @since v3.1.0
     */
    void setTokens(final String key, final long amount);


    /**
     * Add tokens to player's token balance.
     *
     * @param key {@link UUID#toString()} if server is in online mode, otherwise name of the player
     * @param amount Amount to add to player's token balance
     * @param silent true to prevent sending message if target player is online
     * @since v3.1.0
     */
    void addTokens(final String key, final long amount, final boolean silent);


    /**
     * Works the same as {@link #addTokens(String, long, boolean)} with silent defaulting to false.
     *
     * @see #addTokens(String, long, boolean)
     */
    void addTokens(final String key, final long amount);


    /**
     * Remove tokens from player's token balance.
     *
     * @param key {@link UUID#toString()} if server is in online mode, otherwise name of the player
     * @param amount Amount to remove from player's token balance
     * @param silent true to prevent sending message if target player is online
     * @since v3.1.0
     */
    void removeTokens(final String key, final long amount, final boolean silent);


    /**
     * Works the same as {@link #removeTokens(String, long, boolean)} with silent defaulting to false.
     *
     * @see #removeTokens(String, long, boolean)
     */
    void removeTokens(final String key, final long amount);


    /**
     * Reload the modules of the plugin.
     *
     * @return true if reload was successful, otherwise false
     */
    boolean reload();

}
