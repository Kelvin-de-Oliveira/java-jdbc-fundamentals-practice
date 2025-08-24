package br.com.kelvin.persistence;

import br.com.kelvin.persistence.entity.ItemEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    public void insert(final ItemEntity entity) {
        String sql = "INSERT INTO Items (string, item_date, number) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getString());
            stmt.setTimestamp(2, Timestamp.from(entity.getDate().toInstant()));
            stmt.setBigDecimal(3, entity.getNumber());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(final ItemEntity entity) {
        String sql = "UPDATE Items SET string = ?, item_date = ?, number = ? WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getString());
            stmt.setTimestamp(2, Timestamp.from(entity.getDate().toInstant()));
            stmt.setBigDecimal(3, entity.getNumber());
            stmt.setLong(4, entity.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum registro atualizado. Verifique se o ID existe.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final long id) {
        String sql = "DELETE FROM Items WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum registro deletado. Verifique se o ID existe.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemEntity> findAll() {
        String sql = "SELECT * FROM Items";
        List<ItemEntity> items = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItemEntity entity = new ItemEntity();
                entity.setId(rs.getLong("id"));
                entity.setString(rs.getString("string"));
                entity.setDate(rs.getTimestamp("item_date").toInstant().atOffset(java.time.ZoneOffset.UTC));
                entity.setNumber(rs.getBigDecimal("number"));
                items.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public ItemEntity findById(final long id) {
        String sql = "SELECT * FROM Items WHERE id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItemEntity entity = new ItemEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setString(rs.getString("string"));
                    entity.setDate(rs.getTimestamp("item_date").toInstant().atOffset(java.time.ZoneOffset.UTC));
                    entity.setNumber(rs.getBigDecimal("number"));
                    return entity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ItemEntity> findByName(final String name) {
        String sql = "SELECT * FROM Items WHERE string = ?";
        List<ItemEntity> items = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemEntity entity = new ItemEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setString(rs.getString("string"));
                    entity.setDate(rs.getTimestamp("item_date").toInstant().atOffset(java.time.ZoneOffset.UTC));
                    entity.setNumber(rs.getBigDecimal("number"));
                    items.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
