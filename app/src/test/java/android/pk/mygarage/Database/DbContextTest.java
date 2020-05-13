package android.pk.mygarage.Database;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DbContextTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void DbLinker_is_able_to_establish_connection(){
        new DbContext();
    }
}
