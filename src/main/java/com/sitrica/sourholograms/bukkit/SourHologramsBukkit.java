package com.sitrica.sourholograms.bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sitrica.sourholograms.Platform;
import com.sitrica.sourholograms.bukkit.nms.BukkitNMS;
import com.sitrica.sourholograms.bukkit.nms.ProtocolLibNMS_1_19_R2;
import com.sitrica.sourholograms.managers.HologramManager;
import com.sitrica.sourholograms.nms.versions.NMS_1_19_R2;
import com.sitrica.sourholograms.util.Version;

/**
 * Main class for SourHolograms on Bukkit/Spigot.
 */
public class SourHologramsBukkit extends JavaPlugin implements Platform {

	private PlatformNMS<? extends BukkitPlayerReference> nms;
	private Version minecraftVersion, platformVersion;
	private static SourHologramsBukkit instance;
	private HologramManager hologramManager;
	private static boolean protocollib;
	private BukkitPlatformAPI API;

	public void onEnable() {
		instance = this;
		try {
			setPlatform(this);
			String version = Bukkit.getBukkitVersion();
			Matcher matcher = Pattern.compile("\\d+\\.\\d+(\\.\\d+)?").matcher(version);
			if (!matcher.find()) {
				getLogger().severe("Bukkit version " + version + " is not supported.");
				setPlatform(null);
				setEnabled(false);
				return;
			}
			minecraftVersion = new Version(matcher.group());
			platformVersion = new Version(Bukkit.getVersion());
			if (minecraftVersion.isLargerOrEqualTo(1, 19))
				nms = BukkitNMS.get(this, new NMS_1_19_R2());
			// TODO
			//if (minecraftVersion.isLargerOrEqualTo(1, 9))
			//	nms = BukkitNMS.get(new NMS_1_9());
			//if (minecraftVersion.isLargerOrEqualTo(1, 8))
			//	nms = BukkitNMS.get(new NMS_1_8());
			if (nms == null) {
				protocollib = Bukkit.getPluginManager().isPluginEnabled("ProtocolLib");
				if (!protocollib) {
					getLogger().severe("Version " + version + " is not supported.");
					setPlatform(null);
					setEnabled(false);
					return;
				}
				// The server is not running a version that is supported.
				// Last ditch effort to support it using ProtocolLib.
				nms = ProtocolLibNMS_1_19_R2.get(this);
				getLogger().info("Version " + version + " has not been tested yet. Relying on ProtocolLib support.");
			}
			hologramManager = new HologramManager(this);
			API = new BukkitPlatformAPI(nms);
		} catch (IllegalAccessException e) {
			getLogger().severe("The platform has already been initialized.");
			setEnabled(false);
			return;
		}
	}

	/**
	 * @return SourHolograms instance.
	 */
	static SourHologramsBukkit getInstance() {
		return instance;
	}

	/**
	 * If the server has ProtocolLib enabled.
	 * 
	 * @return boolean if ProtocolLib is installed.
	 */
	public static boolean isUsingProtocolLib() {
		return protocollib;
	}

	@Override
	public Version getMinecraftVersion() {
		return minecraftVersion;
	}

	@Override
	public Version getPlatformVersion() {
		return platformVersion;
	}

	@Override
	public Type getType() {
		return Type.BUKKIT;
	}

	/**
	 * Returns a new instance of the platform API for the plugin.
	 * 
	 * @param plugin The plugin to be used for reference in the API.
	 * @return BukkitPlatformAPI wrapped around the Plugin.
	 */
	public BukkitPlatformAPI getPlatformAPI(Plugin plugin) {
		return API.newInstance(plugin, hologramManager);
	}

	/**
	 * SourHolograms internal API implementation.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public BukkitPlatformAPI getPlatformAPI() {
		return API;
	}

	@Override
	public void printMessage(String message) {
		getLogger().info(message);
	}

	@Override
	public HologramManager getHologramManager() {
		return hologramManager;
	}

}
