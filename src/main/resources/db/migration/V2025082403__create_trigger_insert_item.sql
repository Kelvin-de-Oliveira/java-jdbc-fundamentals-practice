DELIMITER $
CREATE TRIGGER tgr_item_audit_insert AFTER INSERT ON Items
FOR EACH ROW
    BEGIN
        INSERT INTO Items_audit(
            item_id,
            string,
            item_date,
            number,
            operation
        ) VALUES (
            NEW.id,
            NEW.string,
            NEW.item_date,
            NEW.number,
            'I'
        );
END $