import org.example.Pajak;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitTestPajak {
    static Pajak pajak;

    @BeforeAll
    static void init(){
        pajak = new Pajak();
    }

    private static Stream<Arguments> provideParameters(){
        return Stream.of(
                Arguments.of(0,3000000),
                Arguments.of(10,10000000),
                Arguments.of(22,30000000),
                Arguments.of(40f,50000000),
                Arguments.of(-1,-5000),
                Arguments.of(-1,1000000000000d)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void testECPajak(double expected, double salary) {
        assertNotNull(pajak);
        assertEquals(expected, pajak.getPajak(salary));
    }

//    BVA TEST USING PARAMETERIZED TEST
//    Boundary vEC1 dan vEC2
    private static Stream<Arguments> BVA1Parameters(){
        return Stream.of(
                Arguments.of(true,3999999),
                Arguments.of(true,4000000),
                Arguments.of(false,4000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA1Parameters")
    public void testBVA1Pajak(boolean expected, double salary) {
        assertNotNull(pajak);
        assertEquals(expected, pajak.getPajak(salary) == 0);
    }

    //    Boundary vEC2 dan vEC3

    private static Stream<Arguments> BVA2Parameters(){
        return Stream.of(
                Arguments.of(true,14999999),
                Arguments.of(true,15000000),
                Arguments.of(false,15000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA2Parameters")
    public void testBVA2Pajak(boolean expected, double salary) {
        assertNotNull(pajak);
        assertEquals(expected, pajak.getPajak(salary) == 10);
    }

    //    Boundary vEC3 dan vEC4

    private static Stream<Arguments> BVA3Parameters(){
        return Stream.of(
                Arguments.of(true,39999999),
                Arguments.of(true,40000000),
                Arguments.of(false,40000001)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA3Parameters")
    public void testBVA3Pajak(boolean expected, double salary) {
        assertNotNull(pajak);
        assertEquals(expected, pajak.getPajak(salary) == 22);
    }

//    Boundary vEC4

    private static Stream<Arguments> BVA4Parameters(){
        return Stream.of(
                Arguments.of(true,999999999998f),
                Arguments.of(true,999999999999f),
                Arguments.of(false,1000000000000d)
        );
    }

    @ParameterizedTest
    @MethodSource("BVA4Parameters")
    public void testBVA4Pajak(boolean expected, double salary) {
        assertNotNull(pajak);
        assertEquals(expected, pajak.getPajak(salary) == 40f);
    }
}
