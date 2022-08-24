package com.thewhitevillager.Utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerUtils {

    public static void fillHotbar(Player player, ItemStack itemStack) {
        for (int i = 0; i < 9; i++) {
            player.getInventory().setItem(i, itemStack);
        }
    }
    public static void clearHotbar(Player player) {
        for (int i = 0; i < 9; i++) {
            player.getInventory().setItem(i, null);
        }
    }

    public static ItemStack getPlayerHead(UUID playerUUID) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(playerUUID));
        head.setItemMeta(meta);
        return head;
    }

    public enum ArmorType {
        LEATHER,
        GOLD,
        CHAINMAIL,
        IRON,
        DIAMOND,
        NETHERITE
    }

//    TODO: add armor class (check if bukkit has an armor type enum), and create equip armor method that takes armor class player and optional color
    public static class Armor {
        ArmorType armorType;
        ItemMeta meta;
        Color color;

        boolean noDefence = false;

        ItemStack helmet;
        ItemStack chestplate;
        ItemStack leggings;
        ItemStack boots;

        public Armor(ArmorType type) {
            this.armorType = type;

            setupArmor();
        }
        public Armor(ArmorType type, ItemMeta itemMeta) {
            this.armorType = type;
            this.meta = itemMeta;

            setupArmor();
        }

        public Armor(Color color) {
            this.armorType = ArmorType.LEATHER;
            this.color = color;

            setupArmor();
        }
        public Armor(Color color, ItemMeta itemMeta) {
            this.armorType = ArmorType.LEATHER;
            this.meta = itemMeta;
            this.color = color;

            setupArmor();
        }

    /**
     * Do before equip
     * @param value
     * @return
     */
    public Armor noDefence(boolean value) {
        this.noDefence = value;
        this.setupArmor();
        return this;
    }

    private void setupArmor() {

        switch (armorType) {
            case LEATHER:
                helmet = new ItemStack(Material.LEATHER_HELMET);
                break;
            case GOLD:
                helmet = new ItemStack(Material.GOLDEN_HELMET);
                break;
            case CHAINMAIL:
                helmet = new ItemStack(Material.CHAINMAIL_HELMET);
                break;
            case IRON:
                helmet = new ItemStack(Material.IRON_HELMET);
                break;
            case DIAMOND:
                helmet = new ItemStack(Material.DIAMOND_HELMET);
                break;
            case NETHERITE:
                helmet = new ItemStack(Material.NETHERITE_HELMET);
                break;
        }

        if (meta != null) {
            helmet.setItemMeta(meta);
        }

        if (color != null) {
            LeatherArmorMeta colorMeta = (LeatherArmorMeta) helmet.getItemMeta();
            colorMeta.setColor(color);
            helmet.setItemMeta(colorMeta);
        }

        if (noDefence) {
            ItemMeta itemMeta = helmet.getItemMeta();
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.key().value(), -5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.key().value(), -5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            helmet.setItemMeta(itemMeta);
        }

        chestplate = helmet.clone();
        leggings = helmet.clone();
        boots = helmet.clone();

        switch (armorType) {
            case LEATHER:
                chestplate.setType(Material.LEATHER_CHESTPLATE);
                leggings.setType(Material.LEATHER_LEGGINGS);
                boots.setType(Material.LEATHER_BOOTS);
                break;
            case GOLD:
                chestplate.setType(Material.GOLDEN_CHESTPLATE);
                leggings.setType(Material.GOLDEN_LEGGINGS);
                boots.setType(Material.GOLDEN_BOOTS);
                break;
            case CHAINMAIL:
                chestplate.setType(Material.CHAINMAIL_CHESTPLATE);
                leggings.setType(Material.CHAINMAIL_LEGGINGS);
                boots.setType(Material.CHAINMAIL_BOOTS);
                break;
            case IRON:
                chestplate.setType(Material.IRON_CHESTPLATE);
                leggings.setType(Material.IRON_LEGGINGS);
                boots.setType(Material.IRON_BOOTS);
                break;
            case DIAMOND:
                chestplate.setType(Material.DIAMOND_CHESTPLATE);
                leggings.setType(Material.DIAMOND_LEGGINGS);
                boots.setType(Material.DIAMOND_BOOTS);
                break;
            case NETHERITE:
                chestplate.setType(Material.NETHERITE_CHESTPLATE);
                leggings.setType(Material.NETHERITE_LEGGINGS);
                boots.setType(Material.NETHERITE_BOOTS);
                break;
        }
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public void equipArmor(Player player) {
            player.getInventory().setHelmet(helmet);
            player.getInventory().setChestplate(chestplate);
            player.getInventory().setLeggings(leggings);
            player.getInventory().setBoots(boots);
        }
    }


    public static void fade(Player player) {
        fade(player, 70/*NOTE: the default is 70; net.kyori.adventure.title; DEFAULT_TIMES*/);
    }
    public static void fade(Player player, int stayTicks) {
//        if (!player.hasResourcePack()) return; //TODO: add warn
        player.sendActionBar(Component.text("Reminder: remove comment on has resource pack check"));
        player.showTitle(Title.title(Component.text("\uEEEE"), Component.empty(), Title.Times.times(Ticks.duration(10), Ticks.duration(stayTicks), Ticks.duration(20))));
    }
}
