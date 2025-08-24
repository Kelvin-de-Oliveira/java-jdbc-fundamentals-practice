DELIMITER $
CREATE TRIGGER tgr_item_audit_delete BEFORE DELETE ON Items
FOR EACH ROW
    BEGIN
        INSERT INTO Items_audit(
            item_id,
            string,
            item_date,
            number,
            operation
        ) VALUES (
            OLD.id,
            OLD.string,
            OLD.item_date,
            OLD.number,
            'D'
        );
END $