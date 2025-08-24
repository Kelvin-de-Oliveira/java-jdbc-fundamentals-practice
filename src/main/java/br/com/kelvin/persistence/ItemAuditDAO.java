package br.com.kelvin.persistence;

import br.com.kelvin.persistence.entity.ItemAuditEntity;
import br.com.kelvin.persistence.entity.OperationType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemAuditDAO {

    public List<ItemAuditEntity> findAll() {
        String sql = "SELECT * FROM ItemsAuditView";
        List<ItemAuditEntity> audits = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                audits.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return audits;
    }

    public List<ItemAuditEntity> findByItemId(long itemId) {
        String sql = "SELECT * FROM ItemsAuditView WHERE item_id = ?";
        List<ItemAuditEntity> audits = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, itemId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    audits.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return audits;
    }

    private ItemAuditEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        ItemAuditEntity entity = new ItemAuditEntity();
        entity.setId(rs.getLong("id"));
        entity.setItemId(rs.getLong("item_id"));
        entity.setString(rs.getString("string"));
        entity.setOldString(rs.getString("old_string"));

        Timestamp itemDateTs = rs.getTimestamp("item_date");
        if (itemDateTs != null) {
            entity.setItemDate(itemDateTs.toInstant().atOffset(java.time.ZoneOffset.UTC));
        }

        Timestamp oldItemDateTs = rs.getTimestamp("old_item_date");
        if (oldItemDateTs != null) {
            entity.setOldItemDate(oldItemDateTs.toInstant().atOffset(java.time.ZoneOffset.UTC));
        }

        entity.setNumber(rs.getBigDecimal("number"));
        entity.setOldNumber(rs.getBigDecimal("old_number"));

        // Mapeando operation para enum
        String op = rs.getString("operation");
        if (op != null) {
            entity.setOperation(OperationType.valueOf(op));
        }

        Timestamp createAtTs = rs.getTimestamp("create_at");
        if (createAtTs != null) {
            entity.setCreateAt(createAtTs.toInstant().atOffset(java.time.ZoneOffset.UTC));
        }

        return entity;
    }
}
