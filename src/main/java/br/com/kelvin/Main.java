package br.com.kelvin;

import br.com.kelvin.persistence.ItemAuditDAO;
import br.com.kelvin.persistence.ItemDao;
import br.com.kelvin.persistence.entity.ItemAuditEntity;
import br.com.kelvin.persistence.entity.ItemEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final ItemDao dao = new ItemDao();

    public static void main(String[] args) {
        //List<ItemEntity> allItems = new ArrayList<>();

        runMigrations();

        //Inserindo 3 registros no banco
       /* for (int i = 1; i <= 3; i++) {
            ItemEntity item = new ItemEntity();
            item.setString("Item " + i);
            item.setDate(OffsetDateTime.now());
            item.setNumber(BigDecimal.valueOf(i * 10));
            dao.insert(item);
            System.out.println("Inserido: " + item);
        }

        // Buscar todos
        System.out.println("\n=== Todos os Itens ===");
        allItems = dao.findAll();
        allItems.forEach(System.out::println);

        // Buscar por ID
        System.out.println("\n=== Buscar por ID ===");
        ItemEntity foundById = dao.findById(5);
        System.out.println(foundById);

        // Buscar por Nome
        System.out.println("\n=== Buscar por Nome ===");
        List<ItemEntity> foundByName = dao.findByName("Item 2");
        foundByName.forEach(System.out::println);

          //Atualizando
        ItemEntity itemToUpdate = new ItemEntity();
        itemToUpdate.setString("Arroz");
        itemToUpdate.setDate(OffsetDateTime.now());
        itemToUpdate.setNumber(BigDecimal.valueOf(10));
        dao.insert(itemToUpdate);
        itemToUpdate.setString("Arroz Integral");
        itemToUpdate.setNumber(BigDecimal.valueOf(12));
        dao.update(itemToUpdate);
        //Após update
        System.out.println("\nApós update do item2:");
        ItemEntity updatedItem = dao.findById(itemToUpdate.getId());
        System.out.println(updatedItem.getId() + " - " + updatedItem.getString() + " - " + updatedItem.getNumber());

        //Delete
        ItemEntity itemToDelete = new ItemEntity();
        itemToDelete.setString("Item to exclude");
        itemToDelete.setDate(OffsetDateTime.now());
        itemToDelete.setNumber(BigDecimal.valueOf(10));
        dao.insert(itemToDelete);
        System.out.println(itemToDelete.getId());
        dao.delete(9);
        // após a deleção
        allItems = dao.findAll();
        allItems.forEach(i -> System.out.println(i.getId() + " - " + i.getString()));  */


        ItemAuditDAO auditDao = new ItemAuditDAO();
        // Buscar todos os registros da view
        List<ItemAuditEntity> allAudits = auditDao.findAll();
        allAudits.forEach(a -> System.out.println(a));

        // Buscar por item_id específico
        long itemIdToCheck = 1L;
        List<ItemAuditEntity> auditsForItem = auditDao.findByItemId(itemIdToCheck);
        auditsForItem.forEach(a -> System.out.println(a));



    }

    private static void runMigrations() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbc_fundamentals", "root", "123456789")
                .load();
        flyway.repair();
        flyway.migrate();
    }

}


