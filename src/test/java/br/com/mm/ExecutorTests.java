package br.com.mm;

import br.com.mm.dominio.LimiteTest;
import br.com.mm.dominio.PlanaltoTest;
import br.com.mm.dominio.SondaTest;
import br.com.mm.dominio.enumeradores.ComandosTest;
import br.com.mm.dominio.enumeradores.DirecaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LimiteTest.class,
        DirecaoTest.class,
        ComandosTest.class,
        SondaTest.class,
        PlanaltoTest.class
})
public class ExecutorTests {
}
