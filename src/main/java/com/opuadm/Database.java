// those who database
package com.opuadm;

import org.bukkit.entity.Player;
import java.io.File;
import java.sql.*;
import java.util.UUID;

public class Database {
    private final LinuxifyMC plugin;
    public Connection connection;
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS player_data (" +
                    "uuid TEXT PRIMARY KEY, username TEXT NOT NULL, " +
                    "fs_data TEXT, last_updated INTEGER)";

    public Database(LinuxifyMC plugin) {
        this.plugin = plugin;
        initializeDatabase();
    }

    private void initializeDatabase() {
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            plugin.getLogger().warning("Could not create plugin directory");
            return;
        }

        try {
            String dbFile = new File(dataFolder,
                    plugin.getConfig().getString("database.filename", "data.db")).getAbsolutePath();
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA journal_mode = " +
                        plugin.getConfig().getString("database.journal_mode", "WAL") + ";" +
                        "PRAGMA synchronous = " + plugin.getConfig().getInt("database.synchronous", 1) + ";" +
                        "PRAGMA auto_vacuum = " + plugin.getConfig().getInt("database.auto_vacuum", 1) + ";" +
                        "PRAGMA busy_timeout = " + (plugin.getConfig().getInt("database.timeout", 30) * 1000));
                stmt.execute(CREATE_TABLE_SQL);
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Database initialization failed: " + e.getMessage());
        }
    }

    private void createTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS player_data (
                    uuid TEXT PRIMARY KEY,
                    username TEXT NOT NULL,
                    fs_data TEXT,
                    last_updated INTEGER
                )""");
        }
    }

    public void saveData(Player player, String fsData) {
        try {
            if (connection == null || connection.isClosed()) {
                initializeDatabase();
            }

            String sql = "INSERT OR REPLACE INTO player_data (uuid, username, fs_data, last_updated) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, player.getUniqueId().toString());
                stmt.setString(2, player.getName());
                stmt.setString(3, fsData);
                stmt.setLong(4, System.currentTimeMillis());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not save data: " + e.getMessage());
        }
    }

    public String loadFSData(UUID playerUUID) {
        try {
            if (connection == null || connection.isClosed()) {
                initializeDatabase();
            }

            String sql = "SELECT fs_data FROM player_data WHERE uuid = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, playerUUID.toString());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("fs_data");
                    }
                }
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not load data: " + e.getMessage());
        }
        return null;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not close database: " + e.getMessage());
        }
    }
}