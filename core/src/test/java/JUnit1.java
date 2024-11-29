import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import com.ansh.Ground;

import static org.junit.jupiter.api.Assertions.*;
public class JUnit1 {
    @Test
    public void test1()
    {
        Ground g=new Ground();
        int flag=g.getflagStatus();
        assertEquals(0,flag);


    }

}
