package test.yasuchenya.xml.parser;

import com.yasuchenya.xml.entity.Crystal;
import com.yasuchenya.xml.entity.Gemstone;
import com.yasuchenya.xml.exception.ProjectException;
import com.yasuchenya.xml.parser.CrystalStAXBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class CrystalStAXBuilderTest {
    private final static Year DATE_OF_MINING = Year.of(2005);
    private final static Crystal EXPECTED_GEM = new Gemstone("id0001", "string", DATE_OF_MINING, 1.1,
            "string", "string", "opaque", "waxy", "cabochon");
    private final static String EXPECTED_NAME = "a";
    private final static String FILE_PATH = "./src/main/resources/data_xml/test.xml";

    @Test
    public void testGetCrystals() throws ProjectException {
        EXPECTED_GEM.setName(EXPECTED_NAME);
        CrystalStAXBuilder stax = new CrystalStAXBuilder();
        stax.buildSetCrystals(FILE_PATH);
        Set<Crystal> expected = new HashSet<>();
        expected.add(EXPECTED_GEM);
        Set<Crystal> actual = stax.getCrystals();
        if(actual.isEmpty()){
            throw new ProjectException("Set is empty");
        }
        Assert.assertEquals(actual, expected);
    }
}