DELIMITER $
CREATE TRIGGER tgr_item_audit_update BEFORE UPDATE ON Items
FOR EACH ROW
BEGIN
    INSERT INTO Items_audit(
        item_id,
        string,
        old_string,
        item_date,
        old_item_date,
        number,
        old_number,
        operation
    )
    VALUES(
        NEW.id,
        NEW.string,
        OLD.string,
        NEW.item_date,
        OLD.item_date,
        NEW.number,
        OLD.number,
        'U'
    );
END $