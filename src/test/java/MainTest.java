import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;

class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22, unit = SECONDS)
    void main() throws Exception {
        Main.main(new String[]{});
    }
}
