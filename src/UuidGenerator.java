import java.util.UUID;
public class UuidGenerator implements IdGenerator {
    @Override
    public String generarId() {
        return "VSR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    }
}
